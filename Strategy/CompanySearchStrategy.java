package Strategy;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IApplication;
import Interfaces.ISearchStrategy;

public class CompanySearchStrategy implements ISearchStrategy {
    @Override
    public List<IApplication> search(List<IApplication> apps, String query) {
        List<IApplication> results = new ArrayList<>();
        String searchTerm = query.toLowerCase();

        for (IApplication app : apps) {
            if (app.getPosition().toLowerCase().contains(searchTerm)
                    || app.getJobType().toLowerCase().contains(searchTerm)) {
                results.add(app);
            }
        }

        return results;
    }
}
