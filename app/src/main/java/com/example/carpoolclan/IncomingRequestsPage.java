package com.example.carpoolclan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class IncomingRequestsPage extends AppCompatActivity {

    DispatcherController dispatcher;
    TextView homePageRedirect;

    public IncomingRequestsPage() {
        this.dispatcher = new DispatcherController();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incoming_requests_page);

        RecyclerView recyclerView = findViewById(R.id.incoming_requests_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        IncomingRequestsAdapter adapter = new IncomingRequestsAdapter(dispatcher);
        recyclerView.setAdapter(adapter);

        homePageRedirect = findViewById(R.id.home_page_redirect);

        // let users go back to the home page normally
        homePageRedirect.setOnClickListener(view -> {
            Intent intent = new Intent(IncomingRequestsPage.this, HomePage.class);
            startActivity(intent);
        });

        // LISTEN TO INCOMING REQUESTS DB HERE
            // if a new incoming request was added to the DB then:
            // (1) IncomingRequestCard incomingRequest = new IncomingRequestCard(stuff from DB) ---- create a new incoming request card
            // (2) dispatcher.addIncomingRequest(IncomingRequestsCard incomingRequest) ---- add new incoming request card to list
            // (3) adapter.notifyItemInserted(dispatcher.getIncomingRequestsListSize() - 1); ---- notify adapter that a new incoming request card was added in order to display on UI
    }
}
