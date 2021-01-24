package proj.api.util;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Sequencer {
    private Map<String, Map> wardrobe;

    @PostConstruct
    public void init() {
        wardrobe = new HashMap<>();
        Map<String, Sort.Direction> orders = new HashMap<>();
        orders.put("asc", Sort.Direction.ASC);
        orders.put("desc", Sort.Direction.DESC);
        wardrobe.put("order", orders);
    }

    public Sort orderBy(Map<String, Map<String, String>> params) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String parameter : params.keySet()) {
            if (params.get(parameter).isEmpty()) {
                orders.add(Sort.Order.asc(parameter));
            } else {
                orders.addAll(getOrders(parameter, params.get(parameter)));
            }
        }
        return Sort.by(orders);
    }

    private List<Sort.Order> getOrders(String param, Map<String, String> tunes) {
        return tunes.keySet().stream()
                .map(tune -> new Sort.Order((Sort.Direction) wardrobe
                        .get(tune)
                        .get(tunes.get(tune)), param))
                .collect(Collectors.toList());
    }
}
