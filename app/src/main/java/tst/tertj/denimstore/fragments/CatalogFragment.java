package tst.tertj.denimstore.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

import tst.tertj.denimstore.R;
import tst.tertj.denimstore.adapters.ProductsAdapter;
import tst.tertj.denimstore.POJO.Offer;

public class CatalogFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ProductsAdapter productsAdapter;
    private ListView lvOffers;
    private TextView tv_no_products;
    private LinkedList<Offer> offersList = new LinkedList<>();
    private IOnMyOfferListClickListener myOfferListClickListener;

    public CatalogFragment(LinkedList<Offer> offersList) {
        this.offersList = offersList;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        myOfferListClickListener = (IOnMyOfferListClickListener) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        lvOffers = (ListView) view.findViewById(R.id.lvOffers);
        tv_no_products = (TextView) view.findViewById(R.id.tv_no_products);
        lvOffers.setOnItemClickListener(this);
        if(offersList.size() > 0) {
            productsAdapter = new ProductsAdapter(getActivity(), offersList);
            lvOffers.setAdapter(productsAdapter);
        } else{
            tv_no_products.setVisibility(View.VISIBLE);
        }
            return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Offer offer = (Offer) parent.getItemAtPosition(position);
        myOfferListClickListener.onOfferClick(offer);
    }
    public interface IOnMyOfferListClickListener {
        void onOfferClick(Offer selectedOffer);
    }
}
