package com.example.tattannaguilian;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class TextPagerAdapter extends PagerAdapter {

    private final Context context;
    private final String[] texts;

    @Override
    public int getCount() {
        return texts.length;
    }

    public TextPagerAdapter(Context context, String[] texts) {
        this.context = context;
        this.texts = texts;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.page_text, container, false);

        TextView textView = view.findViewById(R.id.pageTextView);
        String text = texts[position];

        // Split the text into English and Ilocano parts
        String[] parts = text.split(" \\("); // Split on " ("
        String englishText = parts[0]; // English part
        String ilocanoText = parts[1].replace(")", ""); // Ilocano part, removing closing parenthesis

        // Combine the parts with different font sizes
        String combinedText = englishText + " (" + ilocanoText + ")";

        // Create a SpannableString to style the text
        SpannableString spannableString = new SpannableString(combinedText);

        // Apply a larger font size to the English part (first part)
        spannableString.setSpan(new AbsoluteSizeSpan(20, true), 0, englishText.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Apply a smaller font size to the Ilocano part (second part)
        spannableString.setSpan(new AbsoluteSizeSpan(25, true), englishText.length() + 2, combinedText.length() - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        // Set the styled text to the TextView
        textView.setText(spannableString);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
