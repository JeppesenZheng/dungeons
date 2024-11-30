package dungeonmania.entities.logic;

public class RuleFactory {
    public static RuleStrategy getRuleStrategy(String rule) {
        switch (rule.toLowerCase()) {
            case "and":
                return new AndStrategy();
            case "or":
                return new OrStrategy();
            case "xor":
                return new XorStrategy();
            case "co_and":
                return new CoAndStrategy();
            default:
                throw new IllegalArgumentException("invalidrule type: " + rule);
        }
    }
}
