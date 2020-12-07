package er;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static void main(String[] args) {

        Map<String, Integer> wordsMap = new HashMap<>();
        List<TextEntry> resultWordList = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS
                | Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(Arrays.toString(args));//  inputText);
        //Extracting words
        String currentWord;
        while (matcher.find()){
            currentWord = matcher.group().toLowerCase();
            Integer count = wordsMap.get(currentWord);
            if(count!=null){
                wordsMap.put(currentWord,  ++count );
            }else {
                wordsMap.put(currentWord,1);
            }
        }

        for(Map.Entry entry : wordsMap.entrySet()){
            resultWordList.add(new TextEntry((String) entry.getKey(), (Integer) entry.getValue()));
        }
        resultWordList.sort(Comparator.comparing(TextEntry::getCount).reversed());

        for(TextEntry text: resultWordList){
            System.out.println(text.getText() + " : " + text.getCount());
        }

    }
}
class TextEntry {
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

}
