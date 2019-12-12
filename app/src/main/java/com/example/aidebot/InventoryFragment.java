package com.example.aidebot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.aidebot.data.adapters.InventoryCardsAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class InventoryFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mContainer = inflater.inflate(R.layout.fragment_inventory, container, false);
        return putInventory(mContainer);
    }


    private View putInventory(View view) {
        LinearLayout linearLayout = view.findViewById(R.id.Layout_inventory);
        ArrayList<HashMap<String, String>> list = getInventory();
        Iterator<HashMap<String, String>> iterator = list.iterator();
        View card[] = new View[list.size()];
        int i = 0;
        while (iterator.hasNext()) {
            HashMap<String, String> next = iterator.next();
            String CN = next.get("CN");
            card[i] = getLayoutInflater().inflate(R.layout.drug_item, null);
            card[i].setId(i);
            InventoryCardsAdapter pr = new InventoryCardsAdapter(card[i], getLayoutInflater(), CN);
            pr.setData(next);
            try {
                linearLayout.addView(pr.getView());
            } catch (Exception e) {
                e.printStackTrace();
            }
            i++;
        }

        return view;
    }

    private ArrayList<HashMap<String, String>> getInventory() {
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> items = new HashMap<>();
        items.put("Name", "Ibuprofeno");
        items.put("Quantity", "50");
        items.put("Exp_date", "2020-10-14");
        items.put("CN", "798116.9");
        list.add(items);
        HashMap<String, String> items2 = new HashMap<>();
        items2.put("Name", "Frenadol");
        items2.put("Quantity", "120");
        items2.put("Exp_date", "2021-12-15");
        items2.put("CN", "965012.4");
        list.add(items2);
        HashMap<String, String> items3 = new HashMap<>();
        items3.put("Name", "Topamax");
        items3.put("Quantity", "30");
        items3.put("Exp_date", "2024-04-02");
        items3.put("CN", "664029.6");
        list.add(items3);
        HashMap<String, String> items4 = new HashMap<>();
        items4.put("Name", "Esteve");
        items4.put("Quantity", "50");
        items4.put("Exp_date", "2020-10-14");
        items4.put("CN", "798116.9");
        list.add(items4);
        HashMap<String, String> items5 = new HashMap<>();
        items5.put("Name", "OLABROS");
        items5.put("Quantity", "120");
        items5.put("Exp_date", "2021-12-15");
        items5.put("CN", "965012.4");
        list.add(items5);
        HashMap<String, String> items6 = new HashMap<>();
        items6.put("Name", "JUAJUAS");
        items6.put("Quantity", "30");
        items6.put("Exp_date", "2024-04-02");
        items6.put("CN", "664029.6");
        list.add(items6);
        HashMap<String, String> items7 = new HashMap<>();
        items7.put("Name", "Ibuprofeno");
        items7.put("Quantity", "50");
        items7.put("Exp_date", "2020-10-14");
        items7.put("CN", "798116.9");
        list.add(items7);
        HashMap<String, String> items8 = new HashMap<>();
        items8.put("Name", "Frenadol");
        items8.put("Quantity", "120");
        items8.put("Exp_date", "2021-12-15");
        items8.put("CN", "965012.4");
        list.add(items8);
        HashMap<String, String> items9 = new HashMap<>();
        items9.put("Name", "Topamax");
        items9.put("Quantity", "30");
        items9.put("Exp_date", "2024-04-02");
        items9.put("CN", "664029.6");
        list.add(items9);
        HashMap<String, String> items10 = new HashMap<>();
        items10.put("Name", "Esteve");
        items10.put("Quantity", "50");
        items10.put("Exp_date", "2020-10-14");
        items10.put("CN", "798116.9");
        list.add(items10);
        HashMap<String, String> items11 = new HashMap<>();
        items11.put("Name", "OLABROS");
        items11.put("Quantity", "120");
        items11.put("Exp_date", "2021-12-15");
        items11.put("CN", "965012.4");
        list.add(items11);
        HashMap<String, String> items12 = new HashMap<>();
        items12.put("Name", "JUAJUAS");
        items12.put("Quantity", "30");
        items12.put("Exp_date", "2024-04-02");
        items12.put("CN", "664029.6");
        list.add(items12);
        return list;
    }

}
