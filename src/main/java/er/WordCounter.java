package er;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static void main(String[] args) {
        StringBuilder sbf
                = new StringBuilder();
        for (String arg : args) {
            sbf.append(arg);
            sbf.append(" ");
        }
        Map<String, Integer> textMap = new HashMap<>();
        List<TextEntry> resultList = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS
                | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sbf);
        String currentWord;
        while (matcher.find()){
            currentWord = matcher.group().toLowerCase();
            if(textMap.containsKey(currentWord)){
                textMap.put(currentWord, (textMap.get(currentWord)+1) );
            }else {
                textMap.put(currentWord,1);
            }
        }
        for(String key : textMap.keySet()){
            resultList.add(new TextEntry(key, textMap.get(key)));
        }
        Collections.sort(resultList);
        for(TextEntry text: resultList){
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
