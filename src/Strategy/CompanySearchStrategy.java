package src.Strategy;
import java.util.ArrayList;
import java.util.List;

import src.Interfaces.IApplication;
import src.Interfaces.ISearchStrategy;

public class CompanySearchStrategy implements ISearchStrategy {
    @Override
    public List<IApplication> search(List<IApplication> apps, String query) {
        List<IApplication> results = new ArrayList<>();
        String searchTerm = query.toLowerCase();

        for (IApplication app : apps) {
            if (app.getPosition().toLowerCase().contains(searchTerm)
                    || app.getCompanyName().toLowerCase().contains(searchTerm)) {
                results.add(app);
            }
        }

        return results;
    }
}
