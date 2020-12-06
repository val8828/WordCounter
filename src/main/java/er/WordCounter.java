package er;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static void main(String[] args) {
        StringBuilder inputText
                = new StringBuilder();
        for (String arg : args) {
            inputText.append(arg);
            inputText.append(" ");
        }
        Map<String, Integer> wordsMap = new HashMap<>();
        List<TextEntry> resultWordList = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS
                | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputText);
        //Extracting words
        String currentWord;
        while (matcher.find()){
            currentWord = matcher.group().toLowerCase();
            if(wordsMap.containsKey(currentWord)){
                wordsMap.put(currentWord, (wordsMap.get(currentWord)+1) );
            }else {
                wordsMap.put(currentWord,1);
            }
        }

        for(String key : wordsMap.keySet()){
            resultWordList.add(new TextEntry(key, wordsMap.get(key)));
        }

        Collections.sort(resultWordList);
        for(TextEntry text: resultWordList){
            System.out.println(text.getText() + " : " + text.getCount());
        }

    }
}
class TextEntry implements Comparable<TextEntry>{
    String text;
    int count;

    public TextEntry(String text, int count) {
        this.text = text;
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(TextEntry o) {
        return Integer.compare(o.count,count);
    }
}
