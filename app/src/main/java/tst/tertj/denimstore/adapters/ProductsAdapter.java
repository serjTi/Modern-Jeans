package tst.tertj.denimstore.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import tst.tertj.denimstore.R;
import tst.tertj.denimstore.constants.Const;
import tst.tertj.denimstore.POJO.Offer;

public class ProductsAdapter extends BaseAdapter {

    private List<Offer> offersList;
    private Context context;

    public ProductsAdapter(Context c, List<Offer> offerList) {
        this.offersList = offerList;
        context = c;
    }



    static class ViewHolder {
        public TextView tvOfferName, tvOfferPrice, tvCountryOfOrigin;
        public ImageView ivMainPicture;
    }

    @Override
    public int getCount() {
        return offersList.size();
    }

    @Override
    public Offer getItem(int position) {
        return offersList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        Offer offer = offersList.get(position);

        if(offer.diller.equals(Const.Dillers.TOS)){
            int drop_price = Integer.parseInt(offer.drop_price);
            int price = drop_price + (drop_price * 35 / 100);
            offer.price = String.valueOf(price);
        }

        // reuse views
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, null);
            // configure view holder
            viewHolder = new ViewHolder();
            viewHolder.tvOfferName = (TextView) convertView.findViewById(R.id.tvOfferName);
            viewHolder.tvOfferPrice = (TextView) convertView.findViewById(R.id.tvOfferPrice);
            viewHolder.tvCountryOfOrigin = (TextView) convertView.findViewById(R.id.tvCountryOfOrigin);
            viewHolder.ivMainPicture = (ImageView) convertView
                    .findViewById(R.id.ivMainPicture);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // fill data
        Glide.with(context).load(offer.images.get(0)).into(viewHolder.ivMainPicture);
        viewHolder.tvOfferName.setText(offer.name);
        viewHolder.tvOfferPrice.setText(offer.price + " " + offer.currencyId);
        if(offer.country_of_origin != null){
            viewHolder.tvCountryOfOrigin.setText("Страна производитель: " + offer.country_of_origin);
        }
        return convertView;
    }
}
