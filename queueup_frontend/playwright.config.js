const { defineConfig } = require('@playwright/test');

module.exports = defineConfig({
  testDir: './tests', // Directory where your tests are stored
  retries: 1,         // Retry failed tests once
  timeout: 30000,     // Timeout for each test in milliseconds
  use: {
    headless: true,   // Run tests in headless mode
    viewport: { width: 1280, height: 720 }, // Set browser size
  },
});

