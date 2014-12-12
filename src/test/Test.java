package test;

import com.xiniu.pos.bean.CategoryBean;
import com.xiniu.pos.support.database.CategoryDBTask;
import com.xiniu.pos.support.utils.TimeUtil;

public class Test {
	public static void main(String[] args) {
		CategoryBean cate = new CategoryBean();
		cate.setId("001");
		cate.setName("手机数码");
		cate.setDescription("This is 3C pd");
		cate.setOrder_index("1");
		cate.setParent_id("1");
		cate.setRow_version("001");
		cate.setIs_deleted("false");
		cate.setCreated_by("misty-rain");
		cate.setCreation_time(TimeUtil.getCurrentTime());
		cate.setLast_updated_by("misty-rain");
		cate.setLast_updated_by(TimeUtil.getCurrentTime());
		

	}

}
