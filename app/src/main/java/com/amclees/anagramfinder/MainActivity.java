package com.amclees.anagramfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Map<String, List<String>> anagramMap;
    private ArrayAdapter<String> adapter;
    private ListView anagramListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnagramFinderApplication application = (AnagramFinderApplication) getApplication();
        anagramMap = application.getAnagramMap();

        List<String> anagramList = new LinkedList<>();
        anagramList.add("No Anagrams Yet");
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, anagramList);

        anagramListView = (ListView) findViewById(R.id.anagramList);
        anagramListView.setAdapter(adapter);
    }

    public void findAnagrams(View view) {
        String wordToSearch = ((TextView) findViewById(R.id.editText)).getText().toString().trim().toLowerCase();
        char[] letters = wordToSearch.toCharArray();
        Arrays.sort(letters);
        String alphabetized = new String(letters);
        adapter.clear();
        if (anagramMap.containsKey(alphabetized)) {
            List<String> anagrams = anagramMap.get(alphabetized);
            anagrams.remove(wordToSearch);
            if (anagrams.size() == 0) {
                adapter.add("No Anagrams");
            } else {
                adapter.addAll(anagrams);
            }
        } else {
            adapter.add("No Anagrams");
        }
    }
}
