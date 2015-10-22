package studio.imedia.vehicleinspection.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import studio.imedia.vehicleinspection.R;
import studio.imedia.vehicleinspection.SubmitOrderActivity;
import studio.imedia.vehicleinspection.bean.Master;
import studio.imedia.vehicleinspection.utils.WidgetUtils;
import studio.imedia.vehicleinspection.views.RoundImageView;

/**
 * Created by eric on 15/10/11.
 */
public class MyMasterEXLVAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<Master> masterList;
    private LayoutInflater inflater;

    public MyMasterEXLVAdapter(Context context, List<Master> masterList) {
        this.context = context;
        this.masterList = masterList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return masterList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return masterList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder holder = null;
        Master master = masterList.get(groupPosition);
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_master_parent, null);
            holder = new GroupViewHolder();
            holder.imgAvatar = (RoundImageView) convertView.findViewById(R.id.img_avatar);
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            holder.ratingBar = (RatingBar) convertView.findViewById(R.id.rating_bar);
            holder.tvServiceAmount = (TextView) convertView.findViewById(R.id.tv_service_amount);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
            holder.imgFolder = (ImageView) convertView.findViewById(R.id.img_folder);
            convertView.setTag(holder);
        } else {
            holder = (GroupViewHolder) convertView.getTag();
        }

        holder.imgAvatar.setImageBitmap(master.getAvatar());
        holder.tvName.setText(master.getName());
        holder.ratingBar.setNumStars(master.getStartNum());
        holder.tvServiceAmount.setText(master.getServiceAmount() + "次服务");
        holder.tvPrice.setText(master.getPrice() + "");
        if (isExpanded) {
            holder.imgFolder.setImageResource(R.drawable.icon_up);
        } else {
            holder.imgFolder.setImageResource(R.drawable.icon_down);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildrenViewHolder holder = null;
        if (null == convertView) {
            convertView = inflater.inflate(R.layout.item_master_children, null);
            holder = new ChildrenViewHolder();
            holder.btnSubmit = (Button) convertView.findViewById(R.id.btn_submit);
            holder.etLeaveMsg = (EditText) convertView.findViewById(R.id.et_leave_msg);
            convertView.setTag(holder);
        } else {
            holder = (ChildrenViewHolder) convertView.getTag();
        }

        holder.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, SubmitOrderActivity.class));
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        private RoundImageView imgAvatar;
        private TextView tvName;
        private RatingBar ratingBar;
        private TextView tvServiceAmount;
        private TextView tvPrice;
        private ImageView imgFolder;
    }

    class ChildrenViewHolder {
        private Button btnSubmit;
        private EditText etLeaveMsg;
    }
}
