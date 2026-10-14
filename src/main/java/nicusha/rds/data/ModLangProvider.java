package nicusha.rds.data;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import nicusha.rds.registry.ItemRegistry;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static nicusha.rds.ReinforcedDiamondSword.MODID;

public class ModLangProvider extends LanguageProvider {
    protected final String locale;
    private final Map<String, String> existingTranslations = new HashMap<>();
    private static final Gson GSON = new Gson();

    public ModLangProvider(PackOutput output, String locale) {
        super(output, MODID, locale);
        this.locale = locale;
        loadExistingTranslations(output);
    }

    private void addAuto(Object key, String englishValue) {
        String translationKey = getTranslationKey(key);
        if (translationKey == null) return;
        if (existingTranslations.containsKey(translationKey)) {
            add(translationKey, existingTranslations.get(translationKey));
            return;
        }
        String targetText = englishValue;
        if (!locale.equals("en_us")) {
            String langCode = locale.split("_")[0];
            targetText = translate(langCode, englishValue);
        }
        add(translationKey, targetText);
    }

    private String getTranslationKey(Object key) {
        if (key instanceof Supplier<?> s) {
            Object val = s.get();
            if (val instanceof Block b) return b.getDescriptionId();
            if (val instanceof Item i) return i.getDescriptionId();
            if (val instanceof EntityType<?> e) return e.getDescriptionId();
        } else {
            if (key instanceof Block b) return b.getDescriptionId();
            if (key instanceof Item i) return i.getDescriptionId();
            if (key instanceof EntityType<?> e) return e.getDescriptionId();
            if (key instanceof String s) return s;
        }
        return null;
    }

    private void loadExistingTranslations(PackOutput output) {
        try {
            Path path = output.getOutputFolder()
                    .resolve("assets")
                    .resolve(MODID)
                    .resolve("lang")
                    .resolve(locale + ".json");

            if (Files.exists(path)) {
                try (Reader reader = Files.newBufferedReader(path)) {
                    JsonObject json = GSON.fromJson(reader, JsonObject.class);
                    if (json != null) {
                        json.entrySet().forEach(entry ->
                                existingTranslations.put(entry.getKey(), entry.getValue().getAsString())
                        );
                    }
                }
            }
        } catch (Exception e) {
            System.out.printf("Could not load existing translations for {}: {}", locale, e.getMessage());
        }
    }

    @Override
    protected void addTranslations() {
        addAuto("itemGroup.rds", "Reinforced Diamond Sword!");

        addAuto(ItemRegistry.copper_sword.get(), "Reinforced Copper Sword");
        addAuto(ItemRegistry.stone_sword.get(), "Reinforced Stone Sword");
        addAuto(ItemRegistry.golden_sword.get(), "Reinforced Golden Sword");
        addAuto(ItemRegistry.iron_sword.get(), "Reinforced Iron Sword");
        addAuto(ItemRegistry.wood_sword.get(), "Reinforced Wooden Sword");
        addAuto(ItemRegistry.diamond_sword.get(), "Reinforced Diamond Sword");
        addAuto(ItemRegistry.netherite_sword.get(), "Reinforced Netherite Sword");
    }

    @Override
    public String getName() {
        return "Languages: " + MODID + " (" + locale + ")";
    }

    public static String translate(String langTo, String text) {
        try {
            String urlStr = "https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=" + langTo + "&dt=t&q=" + URLEncoder.encode(text, StandardCharsets.UTF_8);
            URL url = new URL(urlStr);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) response.append(inputLine);
            in.close();
            String result = response.toString();
            return result.substring(4, result.indexOf("\"", 4));
        } catch (Exception e) {
            return text;
        }
    }
}
