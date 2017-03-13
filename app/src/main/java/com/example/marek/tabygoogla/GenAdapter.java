package com.example.marek.tabygoogla;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;


public class GenAdapter extends BaseAdapter {

    Context mContext;

    private ArrayList<GenVariable> mData = new ArrayList<>();

    private LayoutInflater mInflater;


    public GenAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //mInflater = ((Activity)mContext).getLayoutInflater();
    }


    public void addNewItem (Integer datatype, String text1, String text2, String text3){

        GenVariable mGenTempVariable = new GenVariable();
        mGenTempVariable.setDataAll(datatype,text1,text2,text3);
        mData.add(mGenTempVariable);

        notifyDataSetChanged();
    }

    public void addNewItem (Integer datatype, String text1){

        GenVariable mGenTempVariable = new GenVariable();
        mGenTempVariable.setDataAll(datatype,text1,null,null);
        mData.add(mGenTempVariable);

        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        int itemType;

        itemType = mData.get(position).getDataType();

        return itemType;
    }

    @Override
    public int getViewTypeCount() {
        return MainActivity.TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position).getDataText1();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        GenHolderItem holderListItem = null;

        int type = getItemViewType(position);


        if (convertView == null) {
            holderListItem = new GenHolderItem();
            switch (type) {
                case MainActivity.TYPE_ITEM:

                    convertView = mInflater.inflate(R.layout.list_element_skill, null);
                    holderListItem.textView1 = (TextView)convertView.findViewById(R.id.text);


                    break;
                case MainActivity.TYPE_SEPARATOR:

                    convertView = mInflater.inflate(R.layout.list_element_separator, null);
                    holderListItem.textView1 = (TextView)convertView.findViewById(R.id.textSeparator);


                    break;
                case MainActivity.TYPE_GENERAL:
                    convertView = mInflater.inflate(R.layout.list_element_general, null);
                    break;
                case MainActivity.TYPE_EDUCATION:
                    convertView = mInflater.inflate(R.layout.list_element_education, null);
                    break;
                case MainActivity.TYPE_REFERENCE:
                    convertView = mInflater.inflate(R.layout.list_element_references, null);
                    holderListItem.textView1 = (TextView)convertView.findViewById(R.id.ref_name);
                    holderListItem.textView2 = (TextView)convertView.findViewById(R.id.ref_role);
                    holderListItem.textView3 = (TextView)convertView.findViewById(R.id.ref_tel);
                    holderListItem.imageButton1 = (ImageButton)convertView.findViewById(R.id.ref_call);

                    break;
                case MainActivity.TYPE_LANGUAGE:

                    convertView = mInflater.inflate(R.layout.list_element_language, null);
                    holderListItem.textView1 = (TextView)convertView.findViewById(R.id.lang_name_text);
                    holderListItem.imageView1_1 = (ImageView)convertView.findViewById(R.id.lang_rating_1_1);
                    holderListItem.imageView1_2 = (ImageView)convertView.findViewById(R.id.lang_rating_1_2);
                    holderListItem.imageView1_3 = (ImageView)convertView.findViewById(R.id.lang_rating_1_3);
                    holderListItem.imageView1_4 = (ImageView)convertView.findViewById(R.id.lang_rating_1_4);
                    holderListItem.imageView1_5 = (ImageView)convertView.findViewById(R.id.lang_rating_1_5);
                    holderListItem.imageView2_1 = (ImageView)convertView.findViewById(R.id.lang_rating_2_1);
                    holderListItem.imageView2_2 = (ImageView)convertView.findViewById(R.id.lang_rating_2_2);
                    holderListItem.imageView2_3 = (ImageView)convertView.findViewById(R.id.lang_rating_2_3);
                    holderListItem.imageView2_4 = (ImageView)convertView.findViewById(R.id.lang_rating_2_4);
                    holderListItem.imageView2_5 = (ImageView)convertView.findViewById(R.id.lang_rating_2_5);



                    break;
            }
            convertView.setTag(holderListItem);

        } else {

            holderListItem = (GenHolderItem)convertView.getTag();

        }
        switch (type) {
            case MainActivity.TYPE_ITEM:
                holderListItem.textView1.setText(mData.get(position).getDataText1());
                break;
            case MainActivity.TYPE_GENERAL:
                break;
            case MainActivity.TYPE_EDUCATION:
                break;
            case MainActivity.TYPE_REFERENCE:


                holderListItem.textView1.setText(mData.get(position).getDataText1());
                holderListItem.textView2.setText(mData.get(position).getDataText2());
                holderListItem.textView3.setText(mData.get(position).getDataText3());


                holderListItem.imageButton1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Intent refCallIntent;
                        try {

                            refCallIntent = new Intent(Intent.ACTION_DIAL);
                            refCallIntent.setData(Uri.parse("tel:"+mData.get(position).getDataText3()));
                            mContext.startActivity(refCallIntent);
                        } catch (ActivityNotFoundException activityException) {
                            Log.e("Calling a Phone Number", "Call failed", activityException);
                        }
                    }
                });
                break;

            case MainActivity.TYPE_SEPARATOR:
                holderListItem.textView1.setText(mData.get(position).getDataText1());
                break;
            case MainActivity.TYPE_LANGUAGE:
                holderListItem.textView1.setText(mData.get(position).getDataText1());

                switch(mData.get(position).getDataText2()) {

                    case "1":
                        holderListItem.imageView1_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_2.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView1_3.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView1_4.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView1_5.setBackgroundResource(R.drawable.ic_rating_empty);
                        break;
                    case "2":
                        holderListItem.imageView1_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_2.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_3.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView1_4.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView1_5.setBackgroundResource(R.drawable.ic_rating_empty);
                        break;
                    case "3":
                        holderListItem.imageView1_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_2.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_3.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_4.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView1_5.setBackgroundResource(R.drawable.ic_rating_empty);
                        break;
                    case "4":
                        holderListItem.imageView1_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_2.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_3.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_4.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_5.setBackgroundResource(R.drawable.ic_rating_empty);
                        break;
                    case "5":
                        holderListItem.imageView1_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_2.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_3.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_4.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView1_5.setBackgroundResource(R.drawable.ic_rating_full);
                        break;
                    default:
                        break;
                }

                switch(mData.get(position).getDataText3()) {
                    case "1":
                        holderListItem.imageView2_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_2.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView2_3.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView2_4.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView2_5.setBackgroundResource(R.drawable.ic_rating_empty);
                        break;
                    case "2":
                        holderListItem.imageView2_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_2.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_3.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView2_4.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView2_5.setBackgroundResource(R.drawable.ic_rating_empty);
                        break;
                    case "3":
                        holderListItem.imageView2_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_2.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_3.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_4.setBackgroundResource(R.drawable.ic_rating_empty);
                        holderListItem.imageView2_5.setBackgroundResource(R.drawable.ic_rating_empty);
                        break;
                    case "4":
                        holderListItem.imageView2_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_2.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_3.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_4.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_5.setBackgroundResource(R.drawable.ic_rating_empty);
                        break;
                    case "5":
                        holderListItem.imageView2_1.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_2.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_3.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_4.setBackgroundResource(R.drawable.ic_rating_full);
                        holderListItem.imageView2_5.setBackgroundResource(R.drawable.ic_rating_full);
                        break;
                    default:
                        break;
                }
                break;
        }
        return convertView;
    }

}
