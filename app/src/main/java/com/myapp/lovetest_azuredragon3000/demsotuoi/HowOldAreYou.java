package com.myapp.lovetest_azuredragon3000.demsotuoi;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.myapp.lovetest_azuredragon3000.R;
import com.myapp.lovetest_azuredragon3000.common.DatePickerFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HowOldAreYou extends AppCompatActivity {

    Button chonNgayButton,xemthoigian;
    TextInputLayout ten;
    TextView resultView,ngayThangView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_old_are_you);

        ten = findViewById(R.id.ten);
        chonNgayButton = findViewById(R.id.chonngaysinh);
        xemthoigian = findViewById(R.id.boi);
        ngayThangView = findViewById(R.id.ns);
        resultView = findViewById(R.id.kv);

        xemthoigian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ngayThangView.getText().toString().equals("")){
                    resultView.setText(" vui lòng chọn ngày sinh của bạn !!! ");
                }else{
                    startWorking(ngayThangView.getText().toString());
                }
            }
            private void startWorking(String start_date) {
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
                String current_date = formatter.format(date);
                String days = findDifference(start_date, current_date,"DAYS");
                String bd = start_date;
                resultView.setText(checkngaythang(start_date));
                ngayThangView.setText("bạn sinh ngày "+start_date+" và bạn đả trải qua "+days);
            }

            private String checkngaythang(String start_date) {
                int date = Integer.parseInt(start_date.split(" ")[1]);

                switch (date){
                    case 1:
                        return "Trong tình yêu, những người sinh tháng Giêng thuộc tuýp người khác lãng mạn, ngọt ngào thế nhưng họ rất ngại ngùng khi biểu lộ điều ấy ra bên ngoài. Lúc đầu, đối phương có thể cảm thấy vô vị, nhàm chán nhưng khi hai người đã gắn bó với nhau trong một khoảng thời gian dài, bạn sẽ nhận ra những nét đáng yêu, trẻ con, tinh nghịch của họ";
                    case 2:
                        return "Trong tình yêu, bạn luôn mong muốn được cùng người ấy khám phá, trải nghiệm những điều mới mẻ xung quanh cuộc sống. Người yêu lý tưởng, mối tình lâu dài là những gì bạn khao khát có được. Hai người sẽ có những khoảng thời gian yêu thương tươi đẹp, ý nghĩa không thể quên.";
                    case 3:
                        return "Trong tình yêu, bạn là người chân thành, say đắm, mãnh liệt. Khó ai có thể làm trái tim sắt đá ấy tan chảy, nhưng một khi đã bị chinh phục, bạn sẽ rơi vào vòng xoáy của tình yêu. Hãy dành nhiều thời gian để tìm hiểu đối phương thật kĩ trước khi bắt đầu một mối quan hệ nghiêm túc. Đó sẽ là nền tảng giúp cuộc sống hôn nhân của bạn hòa hợp, hạnh phúc hơn";
                    case 4:
                        return "Những người sinh tháng 4 thường gặp nhiều may mắn trong chuyện tình cảm. Sau khi công việc đã ổn định, bạn sẽ nhanh chóng tìm kiếm người bạn đời phù hợp với mình và tiến đến xây dựng cuộc sống hôn nhân hạnh phúc. Phải thật tỉnh táo trong việc cân bằng thời gian giữa gia đình và sự nghiệp, nếu không bạn sẽ gặp phải một số khó khăn, rắc rối.";
                    case 5:
                        return "Những người này rất ít khi rung động với những mối quan hệ mới gặp gỡ. Tình cảm của họ phải được xây dựng dựa trên sự cảm thông, thấu hiểu và tôn trọng lẫn nhau. Khi công danh, sự nghiệp đã ổn định,  người sinh tháng 5 mới bắt đầu tìm kiếm cho mình một người bạn đời tâm đầu ý hợp. Sau khi kết hôn, dù bận rộn với công việc đến mấy họ cũng dành nhiều thời gian để yêu thương, chăm sóc cho gia đình đặc biệt là người bạn đời.";
                    case 6:
                        return "Trong chuyện tình cảm,người sinh tháng 6 có nhược điểm là “cả thèm chóng chán”. Hôm nay vẫn mãnh liệt, say đắm nhưng ngày mai, không ai biết trái tim của họ sẽ nguội lạnh lúc nào. Họ xem tình yêu như một hương vị cuộc sống, nhưng nếu có cũng được, mà không có thì cũng không vấn đề gì. Bởi dù sao, những người này vẫn yêu tha thiết cuộc sống tự do.";
                    case 7:
                        return "Là một người cởi mở, thân thiện và luôn biết nghĩ cho người khác. Trong tình yêu, họ sẵn sàng hy sinh mọi thứ để người ấy được vui vẻ, hạnh phúc, đôi lúc quên đi bản thân mình.Với bạn bè, những người sinh tháng 7 cũng hết mực chân thành, trung thực, sẵn sàng giúp đỡ hết mình.";
                    case 8:
                        return "Trong tình yêu, bạn khá lãng mạn, ngọt ngào. Luôn dành cho người ấy những bất ngờ thú vị, sẵn sàng làm mọi thứ để họ vui vẻ, hạnh phúc, bạn là mẫu người yêu thật lý tưởng. Dù đôi lúc mối quan hệ xảy ra mâu thuẫn, cãi vã nhưng điều đó sẽ giúp hai bạn thêm yêu thương, thấu hiểu nhau nhiều hơn.";
                    case 9:
                        return "Tế nhị, nhẹ nhàng là đặc điểm nổi bật nhất của những người sinh ra vào giữa mùa thu. Họ luôn biết cách hòa đồng, cởi mở với mọi người, thích giao tiếp, kết bạn ở khắp mọi nơi. Đó là lý do tại sao họ có mối quan hệ xã hội rộng rãi";
                    case 10:
                        return "Trong tình yêu, họ khó có thể kiềm chế cảm xúc và cơn nóng giận của mình. Yêu ghét rõ ràng, theo đuổi đến cùng những thứ mình thích, họ thực sự rất kiên nhẫn với tình cảm của mình. Thế nhưng điểm yếu lớn nhất của những người này lại là dễ bị ảnh hưởng bởi người khác.";
                    case 11:
                        return "Những người sinh tháng 11 khá sắc sảo trong chuyện tình cảm. Bạn yêu bằng lý trí nhiều hơn con tim, biết lúc nào nên yêu, lúc nào nên dừng lại, thẳng thắn và dứt khoát thế nên có những người cho rằng trái tim bạn thật sắt đá, lạnh lùng. Chỉ khi đã xây dựng một sự nghiệp vững chắc, ổn định, bạn mới có thể tập trung tìm kiếm người người bạn đời tâm đầu ý hợp với mình.";
                    case 12:
                        return "Trong tình yêu, dù là nam hay nữ thì bạn luôn đặt sự tự do, thoải mái của mình lên hàng đầu, bởi vậy bạn không thích những người hay ghen tuông một cách mù quáng. Mong ước lớn nhất trong cuộc đời bạn là được cùng người ấy đi du lịch ở khắp mọi nơi, khám phá những điều mới mẻ, thú vị";
                    default:
                        return "Trong tình yêu, dù là nam hay nữ thì bạn luôn đặt sự tự do, thoải mái của mình lên hàng đầu, bởi vậy bạn không thích những người hay ghen tuông một cách mù quáng. Mong ước lớn nhất trong cuộc đời bạn là được cùng người ấy đi du lịch ở khắp mọi nơi, khám phá những điều mới mẻ, thú vị";

                }
            }

            private String findDifference(String start_date, String end_date,String temp) {
                SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
                String ret = "error";
                try {
                    Date date1 = myFormat.parse(start_date);
                    Date date2 = myFormat.parse(end_date);
                    assert date2 != null;
                    assert date1 != null;
                    long diff = date2.getTime() - date1.getTime();

                    if(temp.equals("DAYS")){
                        ret = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)+ " ngày cho đến nay";
                    }else{
                        ret = "error";
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return ret;
            }
        });

        chonNgayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dFragment = new DatePickerFragment(ngayThangView,false);
                dFragment.show(getFragmentManager(), "Date Picker");
            }
        });
    }
}