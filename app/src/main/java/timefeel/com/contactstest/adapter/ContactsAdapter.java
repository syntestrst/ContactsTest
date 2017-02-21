package timefeel.com.contactstest.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import timefeel.com.contactstest.R;
import timefeel.com.contactstest.activity.DetailActivity;
import timefeel.com.contactstest.models.Contact;
import timefeel.com.contactstest.models.ContactsResponse;

/**
 * Created by test on 18/02/2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private static List<Contact> contacts;
    private int rowLayout;
    private Context context;
    private static final String TAG = ContactsAdapter.class.getSimpleName();


    public ContactsAdapter(List<Contact> contacts, int rowLayout, Context context) {
        this.contacts = contacts;
        this.rowLayout = rowLayout;
        this.context = context;

    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {

        CardView contactsLayout;
        TextView name;
        TextView email;
        TextView phone;

        public ContactsViewHolder(View itemView) {
            super(itemView);
            contactsLayout = (CardView) itemView.findViewById(R.id.contacts_layout);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            phone = (TextView) itemView.findViewById(R.id.phone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(TAG, "Element" + getAdapterPosition());
                    Context context = view.getContext();
                    // get position
                    int pos = getAdapterPosition();
                    // check if item still exists
                    if (pos != RecyclerView.NO_POSITION) {
                        Contact clickedDataItem = contacts.get(pos);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("name", contacts.get(pos).getName().getFirst());
                        intent.putExtra("email", contacts.get(pos).getEmail());
                        intent.putExtra("phone", contacts.get(pos).getCell());
                       // intent.putExtra("image", contacts.get(pos).getPicture().getThumbnail());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(view.getContext(), "You clicked " + clickedDataItem.getEmail(), Toast.LENGTH_SHORT).show();
                    }
                }});
        }
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contacts, parent, false);
        return new ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getName().getFirst());
        holder.email.setText(contacts.get(position).getEmail());
        holder.phone.setText(contacts.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


}