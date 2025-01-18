package com.example.tattannaguilian;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<String> {

    private final List<String> items;
    private final LayoutInflater inflater;

    public CustomArrayAdapter(@NonNull Context context, @NonNull List<String> objects) {
        super(context, R.layout.list_item, objects);
        items = objects;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.textView);
        String item = items.get(position);
        textView.setText(item);

        // Set background color based on category
        if (item.equals("Falls") || item.equals("Farm / Ranch / Theme Park") || item.equals("Historic Monument") ||
                item.equals("Historic Site / Archeological Sites") || item.equals("Leisure-land / Theme Park") ||
                item.equals("Nature Trail Path") || item.equals("Religious Site") || item.equals("River & Landscape") ||
                item.equals("Churches") || item.equals("Municipal")) {
            textView.setBackgroundColor(0xFFA7CFBF); // Bluegreen color for folders
        } else if (item.equals("Minzi Falls") || item.equals("Sangbay Falls") || item.equals("Tuddingan Falls") ||
                item.equals("Naguilian Basi Processing Center") || item.equals("San Antonio Mini Rice Terraces") ||
                item.equals("Mangkaeng Memorial Site") || item.equals("Gabaldon Building (Dr. Hermogenes F. Belen Elementary School)") ||
                item.equals("Camp Lt. Col. Jose M. Laberinto") || item.equals("Tomb Of The Unknown Soldiers") ||
                item.equals("Ivy's Garden Amore") || item.equals("Naguilian Bougainvillea Park") ||
                item.equals("Naguilian Eco Sanctuary") || item.equals("Naguilian Town Plaza") ||
                item.equals("Naguilian Watershed") || item.equals("Daughters Of Mary Consolatrix Monastery") ||
                item.equals("Baraoas Sur Rapids") || item.equals("Saint Augustine Of Hippo Parish") ||
                item.equals("Sangbay Falls") || item.equals("Municipal of Naguilian La, Union")) {
            textView.setBackgroundColor(0xFFA1FBD6); // White bluegreen color for subcategories
        } else {
            textView.setBackgroundColor(0x00000000); // Transparent for others
        }

        return convertView;
    }
}
