package com.example.carpoolclan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ViewPotentialOffersPage extends AppCompatActivity implements PotentialOffersAdapter.OffersButtonClickListener {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<PotentialOffersCard> offersCardList;
    PotentialOffersAdapter adapter;
    TextView homePageRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_potential_offers_page);
        initData();
        initRecyclerView();

        homePageRedirect = findViewById(R.id.home_page_redirect);

        // let users go back to the home page; display an alert to notify about unsaved changes
        homePageRedirect.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewPotentialOffersPage.this);
            builder.setMessage("Are you sure you want to leave this page? Any changes you made will not be saved.");
            builder.setTitle("Unsaved Changes");
            builder.setCancelable(false);

            builder.setPositiveButton("Yes", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Your changes have not been saved", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewPotentialOffersPage.this, MakeRequestsPage.class);
                startActivity(intent);
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }

    /*
     * This function initializes the RecyclerView. A RecyclerView is a view that gets constantly
     * added. In this case, the view that gets constantly added is the potential offers card.
     */
    private void initRecyclerView() {
        // designing how recyclerView looks like in activity_view_potential_offers_page.xml
        recyclerView = findViewById(R.id.potential_offers_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // calling adapter once; passes in the offers list and the button clicker
        adapter = new PotentialOffersAdapter(offersCardList, this);

        //if the list is empty, then dialog box would be displayed prompting users to go modify their request or wait
        if(adapter.getItemCount() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(ViewPotentialOffersPage.this);
            builder.setMessage("There are no offers that match your request! Please modify your request or wait until an Offer has been created!");
            builder.setTitle("No Potential Offers");
            builder.setCancelable(false);

            builder.setPositiveButton("Modify Request", (dialog, which) -> {
                Toast.makeText(getApplicationContext(), "Please modify your request!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewPotentialOffersPage.this, MakeRequestsPage.class);
                startActivity(intent);
            });

            builder.setNegativeButton("Wait for Offers", (dialog, which) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // constantly updates the adapter if new requests come in
        // (NOTE FOR US: IF THE POTENTIAL OFFERS ARE CONSTANTLY GETTING ADDED JUST ADD AFTER THIS LINE)
        // ACTUALLY MIGHT NOT WORK LOL, MIGHT HAVE TO NOTIFY ADAPTER EVERYTIME YOU ADD OR REMOVE
    }
    /*
     * Adding to the ArrayList; this array list is the one being passed into the adapter
     */
    private void initData(){
        offersCardList = new ArrayList<>();

        offersCardList.add(new PotentialOffersCard("000123", "12 minutes", "3", "$12.45", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@"));
        offersCardList.add(new PotentialOffersCard("001249", "9 minutes", "6", "$7.45", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@"));
        offersCardList.add(new PotentialOffersCard("382913", "34 minutes", "1", "$34.45", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@"));
        offersCardList.add(new PotentialOffersCard("696970", "34 minutes", "1", "$34.45", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@"));
    }

    /*
     * Handles what happens if the map button got clicked. This function is from the interface in Potential Offers Adapter.
     */
    @Override
    public void onMapsButtonClick(int position) {
        Toast.makeText(getApplicationContext(), "Showing Potential Route", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewPotentialOffersPage.this, GoogleMapsInterface.class);
        intent.putExtra("polyLine", offersCardList.get(position).getPolyLine()); // position is the index of the PotentialOffersCard (essentially accessing the array list)
        startActivity(intent);
    }

    /*
     * Handles what happens if the confirm button got clicked. This function is from the interface in Potential Offers Adapter.
     */
    @Override
    public void onConfirmRequestButtonClick(int position) {
        Toast.makeText(getApplicationContext(), "Confirming Route", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ViewPotentialOffersPage.this, HomePage.class);
        startActivity(intent);
    }
}
