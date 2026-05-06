package src.Interfaces;
import java.util.List;

public interface ISearchStrategy {
    List<IApplication> search(List<IApplication> apps, String query);
}
