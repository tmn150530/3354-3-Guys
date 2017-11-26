package com.example.contactmanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


public class ViewContact extends AppCompatActivity {

    TextView nameTxt, label1, label2, label3, label4, field1, field2, field3, field4;
    String phone, email, address, group;
    int numberOfLabelsNeeded = 0;   //This keeps track of how many labels we need.

    @Override
    public void onCreate(Bundle SavedInstanceState){

        super.onCreate((SavedInstanceState));
        setContentView((R.layout.activity_view_contact));

        nameTxt = (TextView) findViewById(R.id.txtName);
        label1 = (TextView) findViewById(R.id.label1);
        label2 = (TextView) findViewById(R.id.label2);
        label3 = (TextView) findViewById(R.id.label3);
        label4 = (TextView) findViewById(R.id.label4);
        field1 = (TextView) findViewById(R.id.field1);
        field2 = (TextView) findViewById(R.id.field2);
        field3 = (TextView) findViewById(R.id.field3);
        field4 = (TextView) findViewById(R.id.field4);


        Bundle extras = this.getIntent().getExtras();
        if (extras != null){
            Contact contact = (Contact)extras.getSerializable("CONTACT");
            nameTxt.setText(contact.getName());

            phone = contact.getPhone();
            email = contact.getEmail();
            address = contact.getAddress();
            group = contact.getGroup();

            //If not empty, then set the next available label to say "Phone" and display phone number beneath
            if (phone.compareTo("") != 0){
                numberOfLabelsNeeded++; //Updates counter to know how many labels have been used
                setText("Phone",phone);
            }

            //Same as above, but with email
            if (email.compareTo("") != 0){
                numberOfLabelsNeeded++;
                setText("Email",email);
            }

            if (address.compareTo("") != 0){
                numberOfLabelsNeeded++;
                setText("Address",address);
            }

            if (group.compareTo("") != 0){
                numberOfLabelsNeeded++;
                setText("Group",group);
            }

            hideExtraLabels();  //Makes the labels we don't use invisible.

        }
    }

    private void setText(String label,String info){
        getLabel(numberOfLabelsNeeded).setText(label);
        getField(numberOfLabelsNeeded).setText(info);
    }

    //Returns the next available label based on how many have been used
    private TextView getLabel(int labelNum){
        switch (labelNum){
            case 1: return label1;
            case 2: return label2;
            case 3: return label3;
            case 4: return label4;
        }

        return null;
    }

    //Returns the next available field depending on how many have been used
    private TextView getField(int fieldNum){
        switch (fieldNum){
            case 1: return field1;
            case 2: return field2;
            case 3: return field3;
            case 4: return field4;
        }

        return null;
    }

    //Hides extra labels that are not used
    private void hideExtraLabels(){  //NOTE: This relies on the fall through of the switch statement
        switch (numberOfLabelsNeeded){
            case 0: label1.setText("");
            case 1: label2.setText("");
            case 2: label3.setText("");
            case 3: label4.setText("");
        }
    }
}