package studio.imedia.vehicleinspection.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import studio.imedia.vehicleinspection.R;
import studio.imedia.vehicleinspection.bean.CarInspection;

/**
 * Created by eric on 15/10/10.
 */
public class MyCarInspectionAdapter extends BaseAdapter {

    private Context context;
    private List<CarInspection> carInspectionList;
    private LayoutInflater inflater;

    public MyCarInspectionAdapter(Context context, List<CarInspection> carInspectionList) {
        this.context = context;
        this.carInspectionList = carInspectionList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return carInspectionList.size();
    }

    @Override
    public Object getItem(int position) {
        return carInspectionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        CarInspection carInspection = carInspectionList.get(position);
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_car_inspection, null);
            holder = new ViewHolder();
            holder.tvInspectionType = (TextView) convertView.findViewById(R.id.tv_inspection_type);
            holder.tvSoldCount = (TextView) convertView.findViewById(R.id.tv_sold_count);
            holder.tvPriceOriginal = (TextView) convertView.findViewById(R.id.tv_price_original);
            holder.tvPriceDiscount = (TextView) convertView.findViewById(R.id.tv_price_discount);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvInspectionType.setText(carInspection.getInpectionType());
        holder.tvSoldCount.setText("已售" + carInspection.getSoldCount() + "笔");
        holder.tvPriceOriginal.setText("￥" + carInspection.getPriceOriginal());
        holder.tvPriceOriginal.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);    // 原价添加删除线
        holder.tvPriceDiscount.setText("￥" + carInspection.getPriceDiscount());
        return convertView;
    }

    class ViewHolder {
        private TextView tvInspectionType;
        private TextView tvSoldCount;
        private TextView tvPriceOriginal;
        private TextView tvPriceDiscount;
    }
}
