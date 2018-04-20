package com.qk.party.share;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import com.qk.party.R;

import java.util.Calendar;

/**
 * @AUTHER feona
 * @CREATE 2017/12/11  16:31
 **/
public class TimeUtil {


    private static long time;
    private static int yearC;
    private static int monthC;
    private static int dayC;
    private static int hourC;
    private static int miC;

    public interface Listener {
        void timeOk(long time);
    }

    static Dialog dialog;

    public static void showShareDialog(Context context, final Listener listener) {
        dialog = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.timepk, null);

//        contentView.findViewById(R.id.session).setOnClickListener(listener);
//        contentView.findViewById(R.id.timeline).setOnClickListener(listener);
//        contentView.findViewById(R.id.favorite).setOnClickListener(listener);
        final DatePicker datePicker = (DatePicker) contentView.findViewById(R.id.DatePicker);
        final TimePicker timePicker = (TimePicker) contentView.findViewById(R.id.TimePicker);
        timePicker.setIs24HourView(true);
        timePicker
                .setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay,
                                              int minute) {

                        hourC=hourOfDay;
                        miC=minute;
                    }
                });

        Calendar cl = Calendar.getInstance();
        int year = yearC=cl.get(Calendar.YEAR);
        int month = monthC=cl.get(Calendar.MONTH);
        final int day = dayC=cl.get(Calendar.DAY_OF_MONTH);
        int hour = hourC=cl.get(Calendar.HOUR_OF_DAY);
        int minute = miC=cl.get(Calendar.MINUTE);
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                yearC =year;
                monthC =monthOfYear;
                dayC =dayOfMonth;

            }
        });
        contentView.findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //确认时间

                Calendar cl =Calendar.getInstance();
                cl.set(yearC,monthC,dayC,hourC,miC);

                if(cl.getTimeInMillis()>System.currentTimeMillis()) {
                    listener.timeOk(cl.getTimeInMillis());
                }
                dialog.dismiss();
            }
        });


        dialog.setContentView(contentView);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);


        dialog.show();

    }

}
