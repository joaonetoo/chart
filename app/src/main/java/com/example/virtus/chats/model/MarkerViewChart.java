package com.example.virtus.chats.model;

import android.content.Context;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.TextView;

import com.example.virtus.chats.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MarkerViewChart extends MarkerView {
    private TextView mValueYellow;
    private TextView mValueOrange;
    private TextView mValuePink;
    private TextView mValueGreen;
    private TextView mValueBlue;
    private View mLayoutYellow,mLayoutOrange,mLayoutPink,mLayoutGreen,mLayoutBlue ;

    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    public MarkerViewChart(Context context, int layoutResource) {
        super(context, layoutResource);
        mValueYellow = (TextView) findViewById(R.id.valueYellow);
        mValueOrange = (TextView) findViewById(R.id.valueOrange);
        mValuePink = (TextView) findViewById(R.id.valuePink);
        mLayoutPink = findViewById(R.id.layoutPink);
        mValueGreen = (TextView) findViewById(R.id.valueGreen);
        mValueBlue = (TextView) findViewById(R.id.valueBlue);
        mLayoutYellow = findViewById(R.id.layoutYellow);
        mLayoutOrange = findViewById(R.id.layoutOrange);
        mLayoutPink = findViewById(R.id.layoutPink);
        mLayoutGreen = findViewById(R.id.layoutGreen);
        mLayoutBlue = findViewById(R.id.layoutBlue);

    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        float[] values = (float[]) e.getData();
        if (values[0] > 0){
            mValuePink.setText(formatterValue(values[0]));
            mLayoutPink.setVisibility(View.VISIBLE);
        }else{
            mLayoutPink.setVisibility(View.GONE);
        }
        if (values[1] > 0){
            mValueOrange.setText(formatterValue(values[1]));
            mLayoutOrange.setVisibility(View.VISIBLE);
        }
        if (values[2] > 0){
            mValueYellow.setText(formatterValue(values[2]));
            mLayoutYellow.setVisibility(View.VISIBLE);
        }
        if (values[3] > 0){
            mValueGreen.setText(formatterValue(values[3]));
            mLayoutGreen.setVisibility(View.VISIBLE);
        }
        if (values[4] > 0) {
            mValueBlue.setText(formatterValue(values[4]));
            mLayoutBlue.setVisibility(View.VISIBLE);
        }else{
            mLayoutBlue.setVisibility(View.GONE);

        }

    }

    private MPPointF mOffset;

    @Override
    public MPPointF getOffset() {

        if(mOffset == null) {
            mOffset = new MPPointF(-(getWidth()), -getHeight());
        }

        return mOffset;
    }

    public String formatterValue(float value){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.UP);
        String result = df.format(value);
        String template = "%sKWh";
        return (String.format(template,result));


    }



}
