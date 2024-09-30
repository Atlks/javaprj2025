package mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface Tab1Mapper {

    @Insert("INSERT INTO tab1 (k, v) VALUES (#{k}, #{v})")
    void insertTab1(Map<String, Object> params);
}
