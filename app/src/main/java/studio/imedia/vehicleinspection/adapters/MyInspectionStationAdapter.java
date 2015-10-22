package studio.imedia.vehicleinspection.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import studio.imedia.vehicleinspection.R;
import studio.imedia.vehicleinspection.bean.InspectionStation;

/**
 * Created by eric on 15/10/11.
 */
public class MyInspectionStationAdapter extends BaseAdapter {

    private Context context;
    private List<InspectionStation> inspectionStationList;
    private LayoutInflater inflater;

    private static final int TYPE_SEARCH = 0;
    private static final int TYPE_NORMAL = 1;

    public MyInspectionStationAdapter(Context context, List<InspectionStation> inspectionStationList) {
        this.context = context;
        this.inspectionStationList = inspectionStationList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return inspectionStationList.size();
    }

    @Override
    public Object getItem(int position) {
        return inspectionStationList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (0 == position) {
            return TYPE_SEARCH;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder01 holder01 = null;
        ViewHolder02 holder02 = null;
        int type = getItemViewType(position);
        if (null == convertView) {

            switch (type) {
                case TYPE_SEARCH:
                    holder01 = new ViewHolder01();
                    convertView = inflater.inflate(R.layout.item_search, null);
                    holder01.etSearch = (EditText) convertView.findViewById(R.id.et_search);
                    convertView.setTag(holder01);
                    break;
                case TYPE_NORMAL:
                    holder02 = new ViewHolder02();
                    convertView = inflater.inflate(R.layout.item_inspection_station, null);

                    holder02.imgStation = (ImageView) convertView.findViewById(R.id.img_station);
                    holder02.tvStationName = (TextView) convertView.findViewById(R.id.tv_station_name);
                    holder02.tvPeriodSummer = (TextView) convertView.findViewById(R.id.tv_period_summer);
                    holder02.tvPeriodWinter = (TextView) convertView.findViewById(R.id.tv_period_winter);
                    holder02.ratingBar = (RatingBar) convertView.findViewById(R.id.rating_bar);
                    holder02.tvDistance = (TextView) convertView.findViewById(R.id.tv_distance);
                    holder02.tvDay1 = (TextView) convertView.findViewById(R.id.tv_day1);
                    holder02.tvDay1Value = (TextView) convertView.findViewById(R.id.tv_day1_value);
                    holder02.tvDay2 = (TextView) convertView.findViewById(R.id.tv_day2);
                    holder02.tvDay2Value = (TextView) convertView.findViewById(R.id.tv_day2_value);
                    holder02.tvDay3 = (TextView) convertView.findViewById(R.id.tv_day3);
                    holder02.tvDay3Value = (TextView) convertView.findViewById(R.id.tv_day3_value);
                    holder02.tvDay4 = (TextView) convertView.findViewById(R.id.tv_day4);
                    holder02.tvDay4Value = (TextView) convertView.findViewById(R.id.tv_day4_value);
                    holder02.tvDay5 = (TextView) convertView.findViewById(R.id.tv_day5);
                    holder02.tvDay5Value = (TextView) convertView.findViewById(R.id.tv_day5_value);

                    convertView.setTag(holder02);
                    break;
            }
        } else {
            switch (type) {
                case TYPE_SEARCH:
                    holder01 = (ViewHolder01) convertView.getTag();
                    break;
                case TYPE_NORMAL:
                    holder02 = (ViewHolder02) convertView.getTag();
                    break;
            }
        }

        switch (type) {
            case TYPE_SEARCH:
                break;
            case TYPE_NORMAL:
                InspectionStation inspectionStation = inspectionStationList.get(position - 1);
                holder02.imgStation.setImageBitmap(inspectionStation.getStationPic());
                holder02.tvStationName.setText(inspectionStation.getStationName());

                String startTimeMorningSummer = inspectionStation.getStartTimeMorningSummer();
                String endTimeMorningSummer = inspectionStation.getEndTimeMorningSummer();
                String startTimeAfternoonSummer = inspectionStation.getStartTimeAfternoonSummer();
                String endTimeAfternoonSummer = inspectionStation.getEndTimeAfternoonSummer();
                holder02.tvPeriodSummer.setText(startTimeMorningSummer + "~" + endTimeMorningSummer + "  " +
                        startTimeAfternoonSummer + "~" + endTimeAfternoonSummer + "(夏)");

                String startTimeMorningWinter = inspectionStation.getStartTimeMorningWinter();
                String endTimeMorningWinter = inspectionStation.getEndTimeMorningWinter();
                String startTimeAfternoonWinter = inspectionStation.getStartTimeAfternoonWinter();
                String endTimeAfternoonWinter = inspectionStation.getEndTimeMorningWinter();
                holder02.tvPeriodWinter.setText(startTimeMorningWinter + "~" + endTimeMorningWinter + "  " +
                        startTimeAfternoonWinter + "~" + endTimeAfternoonWinter + "(冬)");

                holder02.ratingBar.setNumStars((int) inspectionStation.getStarNum());
                holder02.tvDistance.setText(inspectionStation.getDistance() + "m");

                holder02.tvDay1.setText(inspectionStation.getDay1() + "日");
                holder02.tvDay2.setText(inspectionStation.getDay2() + "日");
                holder02.tvDay3.setText(inspectionStation.getDay3() + "日");
                holder02.tvDay4.setText(inspectionStation.getDay4() + "日");
                holder02.tvDay5.setText(inspectionStation.getDay5() + "日");

                holder02.tvDay1Value.setText(inspectionStation.getDay1Value() + "");
                holder02.tvDay2Value.setText(inspectionStation.getDay2Value() + "");
                holder02.tvDay3Value.setText(inspectionStation.getDay3Value() + "");
                holder02.tvDay4Value.setText(inspectionStation.getDay4Value() + "");
                holder02.tvDay5Value.setText(inspectionStation.getDay5Value() + "");
                break;
        }


        return convertView;
    }

    class ViewHolder01 {
        private EditText etSearch;
    }

    class ViewHolder02 {
        private ImageView imgStation;
        private TextView tvStationName;

        private TextView tvPeriodSummer;
        private TextView tvPeriodWinter;

        private RatingBar ratingBar;
        private TextView tvDistance;

        private TextView tvDay1;
        private TextView tvDay1Value;
        private TextView tvDay2;
        private TextView tvDay2Value;
        private TextView tvDay3;
        private TextView tvDay3Value;
        private TextView tvDay4;
        private TextView tvDay4Value;
        private TextView tvDay5;
        private TextView tvDay5Value;
    }
}
