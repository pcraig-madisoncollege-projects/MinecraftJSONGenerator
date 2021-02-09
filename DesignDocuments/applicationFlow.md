# Application Flow

Below is the intended flow process a user or admin would take to achieve certain tasks.

### Generate Tellraw Command

1. User chooses "Generate a Command", which is available on all pages.
1. User chooses "Tellraw Command" from list of command generator options.
1. User sets target selector to "Nearest Player (@p)" and adds no additional target selector tags.
1. User adds text component and sets it to "Hello, world!" with a color of red and no other formatting options.
1. User clicks "Generate Command" button.
1. Data is sent to generate command servlet along with command type.
1. Servlet creates tellraw command object from data.
1. Command object generates command from data.
1. Servlet sends command string back to text area at the bottom of the page.

### Generate Title Command

1. User chooses "Generate a Command", which is available on all pages.
1. User chooses "Title Command" from list of command generator options.
1. User sets target selector to "Random Player (@r)" and adds no additional target selector tags.
1. User sets "Title Location" to "title".
1. User adds text component and sets it to "This is a title" with a color of blue and no other formatting options.
1. User clicks "Generate Command" button.
1. Data is sent to generate command servlet along with command type.
1. Servlet creates title command object from data.
1. Command object generates command from data.
1. Servlet sends command string back to text area at the bottom of the page.

### Generate Sign Give Command

1. User chooses "Generate a Command", which is available on all pages.
1. User chooses "Give Sign Command" from list of command generator options.
1. User sets target selector to "All Players (@a)" and adds no additional target selector tags.
1. User sets "Sign Type" to "Spruce Sign".
1. User adds text component and sets it to "Line of text" with a color of white and no other formatting options.
1. User clicks "Generate Command" button.
1. Data is sent to generate command servlet along with command type.
1. Servlet creates give sign command object from data.
1. Command object generates command from data.
1. Servlet sends command string back to text area at the bottom of the page.

### Generate Give Book Command

1. User chooses "Generate a Command", which is available on all pages.
1. User chooses "Give Written Book Command" from list of command generator options.
1. User sets target selector to "Nearest Player (@p)" and adds tag of "[distance=..3]".
1. User sets "Book Title" to "New Book".
1. User sets "Author" to "Jane Doe".
1. User adds text component and sets it to "Books can store up to 50 pages of text" with a color of black and no other formatting options.
1. User clicks "Generate Command" button.
1. Data is sent to generate command servlet along with command type.
1. Servlet creates give book command object from data.
1. Command object generates command from data.
1. Servlet sends command string back to text area at the bottom of the page.

### Generate Give Named Item Command

1. User chooses "Generate a Command", which is available on all pages.
1. User chooses "Give Named Item Command" from list of command generator options.
1. User sets target selector to "Nearest Player (@p)" and adds no additional target selector tags.
1. User sets "Item Type" to "minecraft:diamond_axe".
1. User adds text component and sets text to "Heavy Axe" with a color of dark red and bold selected.
1. User clicks "Generate Command" button.
1. Data is sent to generate command servlet along with command type.
1. Servlet creates give named item command object from data.
1. Command object generates command from data.
1. Servlet sends command string back to text area at the bottom of the page.

### Register User

1. User chooses "Sign Up" on the main navigation bar, which is available on all pages unless the user is already signed
   in.
1. User fills out the sign up form and submits.
1. Request goes to sign up servlet.
1. Servlet creates a user object and then creates user in the database.
1. Response to user confirming new user registration is shown on the sign up page.

### User Sign-in

1. User chooses "Login" on the main navigation bar, which is available on all pages unless the user is already signed
   in.
1. User enters email and password on form and submits.
1. If user is authenticated, the server will handle allowing access to save commands to the user's account. It will also
   redirect the user to the home page and display a "Saved Commands" button in the main navigation bar instead of
   default buttons (Sign In, Login).
1. If authentication fails, show error message on login page.

### About

1. User chooses "About" on the main navigation bar if not already at home page.
1. The main content on the page is static, regardless of sign-in state.

### View Public Command

1. User selects recently shared command or opens link to public command.
1. Page sends a request to view user's command based on unique identifier.
1. Servlet uses the commands dao to select the command according to unique identifier
1. Dao performs select and retrieves the string command from the results.
1. Dao returns specified command to servlet.
1. Servlet sends command back to view public command jsp.
1. View public command jsp displays the command.

### Report Public Command

1. User views publicly available command.
1. User clicks report link found below prompt to generate command in the main content of the view public command jsp.
1. User requests report jsp page from report servlet.
1. User is forwarded to report jsp page by servlet.
1. User enters report information and email if response is needed from admin.
1. User submits report.
1. Report servlet receives request from page and emails support email address with report details.
1. Report servlet sends feedback to user if report failed or succeeded to be sent.
1. Report jsp page shows feedback on page.

### Report (General use)

1. User clicks report link on home page.
1. User requests report jsp page from report servlet.
1. User is forwarded to report jsp page by servlet.
1. User enters report information and email if response is needed from admin.
1. User submits report.
1. Report servlet receives request from page and emails support email address with report details.
1. Report servlet sends feedback to user if report failed or succeeded to be sent.
1. Report jsp page shows feedback on page.

### Save Command to Account

1. User generates command while logged in.
1. User clicks "Save Command" button.
1. Page displays overlay with form to enter command name and category if command has not been saved yet. Otherwise, it
   prompts user to confirm overwriting old command.
1. User submits command to be saved.
1. Page sends a request to save the user's command to the save command.
1. Servlet uses the commands dao to add the generated command and category associated to the user.
1. Dao returns success or failure of saving command.
1. Servlet sends saving feedback to generate command jsp.
1. Generate command jsp displays saving feedback.

### Load Command

1. User navigates to generate command page.
1. User clicks "Load Command" button.
1. Page displays overlay with form to enter command.
1. User submits command.
1. Page sends a request to load command servlet to parse and process entered command.
1. Servlet creates load command object and runs parse method.
1. Load command object returns feedback from success or failure to load.
1. Servlet sends feedback to generate command jsp and command object if loading succeeded.
1. Generate command jsp displays command data loaded from string command.

### Delete Command

1. User navigates to saved commands page when logged in.
1. User requests saved commands list from saved commands servlet.
1. Saved commands servlet calls commands dao to retrieve all commands of the user.
1. Commands dao returns a list of command objects to saved commands servlet.
1. Saved commands servlet sends back all commands saved by user (should probably be paginated).
1. Saved commands jsp page displays jquery data table containing user commands.
1. User finds and clicks delete for a given command.
1. Page sends a request to delete command servlet with specified command as parameter.
1. Remove command servlet forwards user to remove command jsp page to confirm removal.
1. User verifies or cancels removal by clicking "Remove Command" or "Cancel" buttons respectively.
1. Page sends request to remove command servlet to delete the command or back to view user commands servlet if cancelled.
1. Remove command servlet calls command dao to remove the specified command.
1. Command dao returns feedback from removal.
1. Remove command servlet forwards feedback to user.
1. Remove command jsp displays feedback of success or failure as applicable.
