package com.reddit.automation.framework.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Test Data Provider utility class for managing test data
 */
public class TestDataProvider {
    
    private static final Logger logger = LoggerFactory.getLogger(TestDataProvider.class);
    private static final String TEST_DATA_FILE = "src/test/resources/testdata/testdata.json";
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Load test data from JSON file
     */
    public static JsonNode loadTestData() {
        try (InputStream input = new FileInputStream(TEST_DATA_FILE)) {
            JsonNode testData = objectMapper.readTree(input);
            logger.info("Test data loaded successfully from: {}", TEST_DATA_FILE);
            return testData;
        } catch (IOException e) {
            logger.error("Failed to load test data file: {}", TEST_DATA_FILE, e);
            return objectMapper.createObjectNode();
        }
    }
    
    /**
     * Get test users data
     */
    public static List<TestUser> getTestUsers() {
        List<TestUser> users = new ArrayList<>();
        JsonNode testData = loadTestData();
        JsonNode usersNode = testData.get("users");
        
        if (usersNode != null && usersNode.isArray()) {
            for (JsonNode userNode : usersNode) {
                TestUser user = new TestUser();
                user.setUsername(userNode.get("username").asText());
                user.setPassword(userNode.get("password").asText());
                user.setEmail(userNode.get("email").asText());
                user.setValid(userNode.get("valid").asBoolean());
                users.add(user);
            }
        }
        
        logger.info("Loaded {} test users", users.size());
        return users;
    }
    
    /**
     * Get valid test users only
     */
    public static List<TestUser> getValidTestUsers() {
        return getTestUsers().stream()
                .filter(TestUser::isValid)
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Get invalid test users only
     */
    public static List<TestUser> getInvalidTestUsers() {
        return getTestUsers().stream()
                .filter(user -> !user.isValid())
                .collect(java.util.stream.Collectors.toList());
    }
    
    /**
     * Get search terms data
     */
    public static List<String> getSearchTerms() {
        List<String> searchTerms = new ArrayList<>();
        JsonNode testData = loadTestData();
        JsonNode searchTermsNode = testData.get("searchTerms");
        
        if (searchTermsNode != null && searchTermsNode.isArray()) {
            for (JsonNode termNode : searchTermsNode) {
                searchTerms.add(termNode.asText());
            }
        }
        
        logger.info("Loaded {} search terms", searchTerms.size());
        return searchTerms;
    }
    
    /**
     * Get subreddit names
     */
    public static List<String> getSubredditNames() {
        List<String> subreddits = new ArrayList<>();
        JsonNode testData = loadTestData();
        JsonNode subredditsNode = testData.get("subreddits");
        
        if (subredditsNode != null && subredditsNode.isArray()) {
            for (JsonNode subredditNode : subredditsNode) {
                subreddits.add(subredditNode.asText());
            }
        }
        
        logger.info("Loaded {} subreddits", subreddits.size());
        return subreddits;
    }
    
    /**
     * Test User data class
     */
    public static class TestUser {
        private String username;
        private String password;
        private String email;
        private boolean valid;
        
        // Getters and Setters
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
        
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        
        public boolean isValid() { return valid; }
        public void setValid(boolean valid) { this.valid = valid; }
        
        @Override
        public String toString() {
            return "TestUser{" +
                    "username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", valid=" + valid +
                    '}';
        }
    }
}
