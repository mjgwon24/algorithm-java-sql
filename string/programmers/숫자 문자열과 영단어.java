class Solution {
    static StringBuilder sb = new StringBuilder();
    
    public int solution(String s) {
        dfs(s);

        int answer = Integer.parseInt(sb.toString());
        return answer;
    }
    
    static void dfs(String s) {
        if (s.isEmpty()) return;
        
        if (s.startsWith("zero")) {
            sb.append("0");
            dfs(s.substring(4));
        } else if (s.startsWith("one")) {
            sb.append("1");
            dfs(s.substring(3));
        } else if (s.startsWith("two")) {
            sb.append("2");
            dfs(s.substring(3));
        } else if (s.startsWith("three")) {
            sb.append("3");
            dfs(s.substring(5));
        } else if (s.startsWith("four")) {
            sb.append("4");
            dfs(s.substring(4));
        } else if (s.startsWith("five")) {
            sb.append("5");
            dfs(s.substring(4));
        } else if (s.startsWith("six")) {
            sb.append("6");
            dfs(s.substring(3));
        } else if (s.startsWith("seven")) {
            sb.append("7");
            dfs(s.substring(5));
        } else if (s.startsWith("eight")) {
            sb.append("8");
            dfs(s.substring(5));
        } else if (s.startsWith("nine")) {
            sb.append("9");
            dfs(s.substring(4));
        } else if (Character.isDigit(s.charAt(0))) {
            sb.append(s.charAt(0));
            dfs(s.substring(1));
        }
    }
}
