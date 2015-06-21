package stackoverflow.net.icare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import stackoverflow.net.icare.R;
import stackoverflow.net.icare.utill.DoctorProfile;

/**
 * Created by Prodip on 6/21/2015.
 */
public class DoctorAdapter extends ArrayAdapter<String> {



    Context context;
    ArrayList<DoctorProfile> doctorProfiles;
    public DoctorAdapter(Context context, ArrayList  doctorProfiles) {
        super(context, R.layout.custom_view_doctor,  doctorProfiles);
        this.context = context;
        //this.value = value;
        this. doctorProfiles =  doctorProfiles;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.custom_view_doctor,parent,false);
        TextView txtView = (TextView) rowView.findViewById(R.id.textViewDoctor);
        txtView.setText( doctorProfiles.get(position).getDname().toString());
        return rowView;
    }
}
