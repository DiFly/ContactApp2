package org.difly.contactapp2.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.difly.contactapp2.R;
import org.difly.contactapp2.entity.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    private final LayoutInflater mInflater;
    private final OnContactClickListener mListener;
    private List<Contact> mContacts  = new ArrayList<>();

    public ContactListAdapter(Context context, OnContactClickListener contactClickListener) {
        mInflater = LayoutInflater.from(context);
        mListener = contactClickListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact current = mContacts.get(position);
        holder.contactItemView.setText(current.getFirstname() + " " + current.getLastname());
        holder.itemView.setOnClickListener(v -> mListener.onContactClick(current));
    }

    public void setContacts(List<Contact> contacts){
        mContacts.clear();
        mContacts.addAll(contacts);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mContacts != null)
            return mContacts.size();
        else return 0;
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView contactItemView;

        private ContactViewHolder(View itemView) {
            super(itemView);
            contactItemView = itemView.findViewById(R.id.textView);
        }
    }
}
