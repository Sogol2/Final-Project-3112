import java.util.ArrayList;
import java.util.List;

public class StatusSearchStrategy implements ISearchStrategy {
    @Override
    public List<IApplication> search(List<IApplication> apps, String query) {
        List<IApplication> results = new ArrayList<>();

        for (IApplication app : apps) {
            if (app.getStatus().name().equalsIgnoreCase(query)) {
                results.add(app);
            }
        }

        return results;
    }
}
