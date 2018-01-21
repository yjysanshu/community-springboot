import com.lxl.admin.models.Menu;
import com.lxl.common.util.JSONUtil;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/9.
 */
public class Main {
    public static void main(String args[]) throws IOException {
        /*Map map = JSONUtil.jsonToMap("{\"title\":\"角色管理\",\"path\":\"system/role\",\"icon\":\"\",\"seq\":7,\"type\":\"default\"}");
        System.out.println(map.get("title"));*/
        Menu menu = new Menu();
        menu.setTitle("1111");
        menu.setPath("2222");
        menu.setSeq(8);
        System.out.println(JSONUtil.menuToJson(menu));
    }
}
