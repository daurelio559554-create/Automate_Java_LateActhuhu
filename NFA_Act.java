package NFA;


	import java.util.*;

	public class NFA_Act {
	  
	    private static Map<String, Map<Character, Set<String>>> nfa = new HashMap<>();

	    static {
	        nfa.put("q0", Map.of(
	            'a', new HashSet<>(Arrays.asList("q0", "q1")),
	            'b', new HashSet<>(Arrays.asList("q0"))
	        ));
	        nfa.put("q1", Map.of(
	            'b', new HashSet<>(Arrays.asList("q2"))
	        ));
	        nfa.put("q2", Map.of(
	            'a', new HashSet<>(Arrays.asList("q2")),
	            'b', new HashSet<>(Arrays.asList("q2"))
	        ));
	    }

	    public static boolean simulate(String input) {
	        Set<String> currentStates = new HashSet<>();
	        currentStates.add("q0");

	        for (char symbol : input.toCharArray()) {
	            Set<String> nextStates = new HashSet<>();
	            for (String state : currentStates) {
	                Map<Character, Set<String>> transitions = nfa.get(state);
	                if (transitions != null && transitions.containsKey(symbol)) {
	                    nextStates.addAll(transitions.get(symbol));
	                }
	            }
	            currentStates = nextStates;
	        }

	        return currentStates.contains("q2");
	    }

	    public static void main(String[] args) {
	        Scanner sc = new Scanner(System.in);
	        System.out.print("Input: ");
	        String input = sc.nextLine();

	        if (simulate(input))
	            System.out.println("Output: Accepted");
	        else
	            System.out.println("Output: Rejected");

	        sc.close();
	    }
	}


