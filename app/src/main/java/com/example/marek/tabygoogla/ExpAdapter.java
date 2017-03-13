package com.example.marek.tabygoogla;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Marek on 18/12/2016.
 */

public class ExpAdapter extends BaseExpandableListAdapter
{


    private LayoutInflater inflater;
    private ArrayList<ExpParent> parents;
    Context mContext;

    public ExpAdapter(Context mContext, ArrayList<ExpParent> receivedParents )
    {
        // Create Layout Inflator
        //inflater = LayoutInflater.from(this);
        this.mContext = mContext;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.parents = receivedParents;
        //mInflater = ((Activity)mContext).getLayoutInflater();
    }


    // This Function used to inflate parent rows view

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parentView)
    {
        final ExpParent parent = parents.get(groupPosition);

        // Inflate grouprow.xml file for parent rows
        convertView = inflater.inflate(R.layout.list_element_experience_parent, parentView, false);

        // Get grouprow.xml file elements and set values
        ((TextView) convertView.findViewById(R.id.role_title)).setText(parent.getText1());
        ((TextView) convertView.findViewById(R.id.role_company)).setText(parent.getText2());
        ((TextView) convertView.findViewById(R.id.role_time)).setText(parent.getText3());
        ImageView image=(ImageView)convertView.findViewById(R.id.role_icon);
        image.setImageResource(mContext.getResources().getIdentifier("com.example.marek.tabygoogla:mipmap/ic_comp_"+parent.getName(),null,null));

        ImageView arrow=(ImageView)convertView.findViewById(R.id.role_arrow);
        if (isExpanded) {
            arrow.setImageResource(R.drawable.ic_exp_up);
        } else {
            arrow.setImageResource(R.drawable.ic_exp_down);
        }

        return convertView;
    }


    // This Function used to inflate child rows view
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parentView)
    {
        final ExpParent parent = parents.get(groupPosition);
        final ExpChild child = parent.getChildren().get(childPosition);

        // Inflate childrow.xml file for child rows
        convertView = inflater.inflate(R.layout.list_element_experience_child, parentView, false);

        // Get childrow.xml file elements and set values
        ((TextView) convertView.findViewById(R.id.role_resposibility)).setText(child.getText1());

        return convertView;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition)
    {
        return parents.get(groupPosition).getChildren().get(childPosition);
    }

    //Call when child row clicked
    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        int size=0;
        if(parents.get(groupPosition).getChildren()!=null)
            size = parents.get(groupPosition).getChildren().size();
        return size;
    }


    @Override
    public Object getGroup(int groupPosition)
    {
        Log.i("ExpParent", groupPosition+"=  getGroup ");

        return parents.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return parents.size();
    }

    //Call when parent row clicked
    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public void notifyDataSetChanged()
    {
        super.notifyDataSetChanged();
    }

    @Override
    public boolean isEmpty()
    {
        return ((parents == null) || parents.isEmpty());
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }

    @Override
    public boolean hasStableIds()
    {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

}
