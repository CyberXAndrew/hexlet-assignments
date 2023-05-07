package exercise;

//import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    private String tagName;
    private Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    public String getName() {
        return this.tagName;
    }
    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("<" + tagName);

        attributes.entrySet().stream()
                .forEach(pair -> sb.append(String.format(" %s=\"%s\"", pair.getKey(), pair.getValue())));
        return sb.append(">").toString();
    }
}
// END
