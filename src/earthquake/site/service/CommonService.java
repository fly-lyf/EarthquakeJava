package earthquake.site.service;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Created by fly on 2017/6/14.
 */

@Service
public class CommonService<T> {

    public HashMap<String, Object> formMap(T form){
        HashMap<String, Object> attrsMap = new HashMap<>();
        Field[] fields = form.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String attrname = field.getName();
            try {
                Object value = field.get(form);
                if (value != null) {
                    attrsMap.put(attrname, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return attrsMap;
    }
}
