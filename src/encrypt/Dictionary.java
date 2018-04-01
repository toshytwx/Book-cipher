package encrypt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Dictionary {
    private static final String ukrLetters = " абвгдеєжзиіїйклмнопрстуфхцчшщьюяАБВГДЕЄЖЗИІЇКЛМНОПРСТУФХЦЧШЩЬЮЯ1234567890,.()!@#$%^&*_-+=~`";
    private static final String engLetters = " abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890,.()!@#$%^&*_-+=~`";
    public static final ArrayList<?> ENGALPHABET = new ArrayList<>(
            engLetters.chars()
                    .mapToObj(e -> (char) e)
                    .collect(
                            Collectors.toList()
                    )
    );
    public static final ArrayList<?> UKRALPHABET = new ArrayList<>(
            ukrLetters.chars()
                    .mapToObj(e -> (char) e)
                    .collect(
                            Collectors.toList()
                    )
    );
    public static ArrayList<?> CURRENTALPHABET = null;
}
