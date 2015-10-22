package studio.imedia.vehicleinspection.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import studio.imedia.vehicleinspection.R;
import studio.imedia.vehicleinspection.bean.Coupon;

/**
 * Created by eric on 15/10/12.
 */
public class MyCouponAdapter extends BaseAdapter {

    private Context context;
    private List<Coupon> couponList;
    private LayoutInflater inflater;

    public MyCouponAdapter(Context context, List<Coupon> couponList) {
        this.context = context;
        this.couponList = couponList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return couponList.size();
    }

    @Override
    public Object getItem(int position) {
        return couponList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Coupon coupon = couponList.get(position);
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_coupon, null);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.tvDeadline = (TextView) convertView.findViewById(R.id.tv_deadline);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvPrice.setText(coupon.getPrice() + "");
        int year = coupon.getYear();
        int month = coupon.getMonth();
        int day = coupon.getDay();
        holder.tvDeadline.setText("·" + year + "年" + month + "月" + day + "日前使用");
        return convertView;
    }

    class ViewHolder {
        private TextView tvPrice;
        private TextView tvDeadline;
    }
}
