// Time Complexity : O(nk+ml) l --> avg length of words in dictionary; n --> number of words in dictionary
// Space Complexity : O(nk+ml) m --> total number of words; k --> avg length of words in sentence
// Did this code successfully run on Leetcode : Yes

class Solution {
    //structure of the class
    class TrieNode{
        boolean isEnd;
        TrieNode[] children;
        public TrieNode(){
            this.children = new TrieNode[26];
        }
    }
    private void insert(TrieNode root, String word){
        TrieNode curr = root;
        for(int i = 0; i< word.length();i++){
            char c = word.charAt(i);
            if(curr.children[c-'a'] == null)
                curr.children[c-'a'] = new TrieNode();
            curr = curr.children[c-'a'];    
        }
        curr.isEnd = true;
    }


    public String replaceWords(List<String> dictionary, String sentence) {
        //insert words in the dictionary
        TrieNode root = new TrieNode();
        for(String str : dictionary){
            insert(root, str);
        }
        //coming to sentence, splitting words based on spaces 
        //to get array of all the words
        String[] strArr = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for(int i = 0; i< strArr.length; i++){
            String word = strArr[i];
            TrieNode curr = root;
            StringBuilder replacement = new StringBuilder();
            //getting the character 1 by 1 from the word
            for(int k = 0; k<word.length(); k++){
                char c = word.charAt(k);
                if(curr.children[c-'a'] == null || curr.isEnd){
                break;
                }
                curr = curr.children[c-'a'];
                replacement.append(c);
            }
            if(curr.isEnd){
                result.append(replacement);
                result.append(" ");
            }else{
                result.append(word);
                result.append(" ");
            }
        }  
        return result.toString().trim();  
        
    }
}
