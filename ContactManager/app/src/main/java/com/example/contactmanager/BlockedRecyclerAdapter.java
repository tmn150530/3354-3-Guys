package com.example.contactmanager;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adapter for the Blocked RecyclerView
 */
public class BlockedRecyclerAdapter extends RecyclerView.Adapter<BlockedRecyclerAdapter.BlockedContactViewHolder>{
    private int mNumItems;
    private ArrayList<Contact> contactList;
    MainActivity mainActivity;

    public BlockedRecyclerAdapter(int numItems, MainActivity mainActivity){
        mNumItems = numItems;
        this.mainActivity = mainActivity;
    }

    /**
     * Viewholder object for each blocked contact to display
     */
    public class BlockedContactViewHolder extends RecyclerView.ViewHolder{
        public final TextView contactNameTextView;
        public final ImageView contactImageView;

        public BlockedContactViewHolder(View itemView) {
            super(itemView);

            contactNameTextView = (TextView) itemView.findViewById(R.id.contactName);
            contactImageView = (ImageView) itemView.findViewById(R.id.imgListProfilePicture);
        }
    }


    @Override
    public BlockedContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.recyclerview_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        BlockedContactViewHolder viewHolder = new BlockedContactViewHolder(view);



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BlockedContactViewHolder holder, final int position) {
        Contact contact = contactList.get(position);
        String contactInfo = contact.getName();

        holder.contactNameTextView.setText(contactInfo);
        holder.contactImageView.setImageURI(Uri.parse(contact.getImageUri()));

        holder.contactNameTextView.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View view){
                mainActivity.viewBlockedContact(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (contactList != null) {
            return contactList.size();
        }
        return 0;
    }

    public void updateList(ArrayList<Contact> list){
        contactList = list;
        notifyDataSetChanged();
    }


}
