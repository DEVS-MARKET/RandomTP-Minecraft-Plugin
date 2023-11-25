package uk.whitedev.rtp.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {

    private static final Pattern rgbPattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String formatColor(String value) {
        Matcher matcher = rgbPattern.matcher(value);
        while (matcher.find()) {
            String hex = value.substring(matcher.start(), matcher.end());
            ChatColor rgb = ChatColor.of(hex);
            if (matcher.start() != 0 && matcher.end() != value.length()) {
                if (value.charAt(matcher.start() - 1) == '&') {
                    hex = "&" + hex;
                }
                if (value.charAt(matcher.start() - 1) == '{' && value.charAt(matcher.end()) == '}') {
                    hex = "{" + hex + "}";
                }
            }
            value = value.replace(hex, rgb + "");
            matcher = rgbPattern.matcher(value);
        }
        return org.bukkit.ChatColor.translateAlternateColorCodes('&', value).replace("\\n", "\n");
    }
}

