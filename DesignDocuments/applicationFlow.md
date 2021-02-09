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



### User Sign-in



### Password Reset



### Save Command



### Load Command



### Share or Un-share Command



### Remove Saved Command



### Group Saved Commands



### About



### Report Command/User



### Admin Review Reports


