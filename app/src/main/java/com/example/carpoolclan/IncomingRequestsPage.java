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

public class IncomingRequestsPage extends AppCompatActivity implements IncomingRequestsAdapter.RequestsButtonClickListener {

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<IncomingRequestsCard> requestsCardList;
    IncomingRequestsAdapter adapter;
    TextView homePageRedirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_requests_page);
        initData();
        initRecyclerView();

        homePageRedirect = findViewById(R.id.home_page_redirect);

        // let users go back to the home page normally
        homePageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(IncomingRequestsPage.this, HomePage.class);
            startActivity(intent);
        });
    }

    /*
     * This function initializes the RecyclerView. A RecyclerView is a view that gets constantly
     * added. In this case, the view that gets constantly added is the incoming requests card.
     */
    private void initRecyclerView() {
        // designing how recyclerView looks like in activity_incoming_requests_page.xml
        recyclerView = findViewById(R.id.incoming_requests_recycler_view);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        // calling adapter once; passes in the requests list and the button clicker
        adapter = new IncomingRequestsAdapter(requestsCardList, this);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged(); // constantly updates the adapter if new requests come in
        // (NOTE FOR US: IF THE REQUESTS ARE CONSTANTLY GETTING ADDED JUST ADD AFTER THIS LINE)
        // ACTUALLY MIGHT NOT WORK LOL, MIGHT HAVE TO NOTIFY ADAPTER EVERYTIME YOU ADD OR REMOVE, SEE THE LAST FUNCTION ON THIS PAGE

        // if the list is empty, then dialog box would be displayed letting users redirect back to home page
        // THIS ONLY DISPLAYS INITIALLY, IF YOU DELETE ALL REQUESTS AND NOTHING IS THERE IT WONT POP UP
        if (adapter.getItemCount() == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(IncomingRequestsPage.this);
            builder.setMessage("There are currently no matched requests! Make sure to periodically check this page for any incoming requests.");
            builder.setTitle("No Matched Requests");
            builder.setCancelable(false);

            builder.setPositiveButton("OK", (dialog, which) -> {
                Intent intent = new Intent(IncomingRequestsPage.this, HomePage.class);
                startActivity(intent);
            });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    /*
     * Adding to the ArrayList; this array list is the one being passed into the adapter
     */
    private void initData() {
        requestsCardList = new ArrayList<>();

        requestsCardList.add(new IncomingRequestsCard("000123", "12 minutes", "3", "$12.45", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@"));
        requestsCardList.add(new IncomingRequestsCard("001249", "9 minutes", "6", "$7.45", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@"));
        requestsCardList.add(new IncomingRequestsCard("382913", "34 minutes", "1", "$34.45", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@"));
        requestsCardList.add(new IncomingRequestsCard("696970", "34 minutes", "1", "$34.45", "{qagGt_`gNmDc@`@sFbA_Oh@iIn@{JJmAjAeHDOGKi@{@cAuAUKWOgAe@oH_D_E_Ba@[iA{AcA_Bo@aAoAeB{C}DiAoAm@q@w@kA[}@"));
    }

    /*
     * Handles what happens if the map button got clicked. This function is from the interface in Incoming Requests Adapter.
     */
    @Override
    public void onMapsButtonClick(int position) {
        Toast.makeText(getApplicationContext(), "Showing Potential Route", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(IncomingRequestsPage.this, GoogleMapsInterface.class);
        intent.putExtra("polyLine", requestsCardList.get(position).getPolyLine()); // position is the index of the IncomingRequestCard (essentially accessing the array list)
        startActivity(intent);
    }

    /*
     * Handles what happens if the confirm button got clicked. This function is from the interface in Incoming Requests Adapter.
     */
    @Override
    public void onConfirmRequestButtonClick(int position) {
        Toast.makeText(getApplicationContext(), "Confirming Request", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(IncomingRequestsPage.this, HomePage.class);
        startActivity(intent);
    }

    /*
     * Handles what happens if the reject button got clicked. This function is from the interface in Incoming Requests Adapter.
     */
    @Override
    public void onRejectRequestButtonClick(int position) {
        Toast.makeText(getApplicationContext(), "Rejecting Request", Toast.LENGTH_SHORT).show();
        requestsCardList.remove(position);
        adapter.notifyItemRemoved(position); //everytime you remove, must notify the adapter that you want to delete
    }
}
