# Crawler

Part A

Implement your own crawler

Start with crawling the ciir.cs.umass.edu home page
Extract outgoing links from the home page restricting the links to web pages and pdfs that are within cs.umass.edu
Crawl the extracted links respecting robots.txt with 5 second delay
Repeat the above process until you have extracted a total of 100 unique links from pages that you have downloaded. Note that you will (probably) have downloaded far fewer than 100 pages.

Compare the links you found to the links provided with the assignment, to make sure you’re on the right track.

Part B

Explore the growth of the frontier.

Use your implementation to find up to 1000 unique links starting at ciir.cs.umass.edu
Do not limit to cs.umass.edu anymore.
Since we are asking for the number of unique links, and not for you to actually download 1000 files, you shouldn’t need to download and parse more than 200-300 pages for this.
After each page is downloaded and processed, record the number of documents you’ve downloaded and extracted and the number of unique links in the frontier and the visited set at each step in your crawl.
