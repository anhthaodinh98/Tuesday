# TA-Dashboard---Automation framework

- [TA Dashboard - Automation framework](#ta-dashboard---automation-framework)
    * [Install](#install)
        + [Prerequisite](#prerequisite)
        + [Setup](#setup)
    * [Development](#development)
        + [Git work flow](#git-work-flow)
        + [Naming conventions](#naming-conventions)
        + [How to SeleniumCore integrate with TADashboard project](#how-to-SeleniumCore-integrate-with-TADashboard-project)
        + [How to use TADashboard framework](#how-to-use-TADashboard-framework)
            + [Structure project](#project-structure)
            + [Web Driver options](#web-driver-options)
- [SeleniumCore Framework](#selenium-core-framework)
    * [Control](#controls)
        + [Base](#base)
        + [Common](#common)
        + [How to use control](#how-to-use-control)

  # TA Dashboard - Automation framework
  ## Install

  ### Prerequisite
  Below applications required
    - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
    - [Maven](https://maven.apache.org/install.html)
    - [JDK 8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

  ### Setup
    1. Clone source code from Github: https://github.com/SeleniumLV2-2021/TA-Dashboard---ViTeam
    1. To open TADashboard project:
        - Start IntelliJ
        - `File` -> `Open` -> select `TADashboard/pom.xml`
        - Choose `Open as Project` option
    1. Wait for IntelliJ to install all the dependencies from the pom file, if you encounter any error, right click on
       the `pom.xml` file, select `Maven` -> `Reimport`
    1. To import `Selenium Core` dependencies:
        - Open `Maven` from the right bar
        - Click on the `+` icon (Add Maven Projects)
        - Select `SeleniumCore/pom.xml`
    1. Wait for all dependencies to be installed again.
    1. Install all plugins below:
        - Cucumber for Java
        - Gherkin
        - Lombok
    1. AUT:
        - Navigate to link https://drive.google.com/drive/folders/11tFPRMLvaJRn8twVIEtq2XYx16JXYtkL and download
          the `TestArchitect_9.0.0.070_x64.exe` file
        - Run the `TestArchitect_9.0.0.070_x64.exe` and install TA repository
        - Wait until TA repository started
        - Open website: http://localhost/TADashboard/profiles.jsp

  ### Git work flow

  Follow the guide below on how to distribute on current project:

  ## Working with Branch
    1. Checkout `main` branch and pull latest code:
        ```
        git checkout main
        git pull
        ```
    1. Create a new branch from main:
        ```
        git checkout -b <branch-name>
        ```
       Branch name should follow this format: `<author>/<scenario-id>` (i.e. `VH/TC-001`)  
       If it's a branch for fixing/updating code, then use branch purpose instead of scenario id (
       i.e. `VH/fix-naughty-code`)
    1. After finished implementing, commit the code, rebase main
        ```
        git add <updated file path 1> <updated file path 2...>
        git commit -m <commit message>
        git rebase [-i] main (-i: interactive mode, allow edit, remove, squash... commit) 
        ```
    1. Push a new branch to `origin` remote
        ```
        git push origin <branch-name>
        ```
  ### Naming conventions
  #### Packages
  The packages should be named in lower case. In case a package name has 2 or more than 2 words, there should be an underscore among the words, like `<packages_name>`, (e.g. pages, tests).

  #### Files
    - Pages: The pages' name should be in UpperCamelCase, which is uppercase the first letter of each word and put the words be together, like `<PageNamePage>`, (e.g. DashboardPage, HomePage, etc).
    - Definitions: The definitions files' name should be in UpperCamelCase too, like `<FileName>Defs` (e.g. DashboardDefs, CommonDefs, etc)
    - Tests: The tests files' name is similar to Pages and Definitions, like `<FileName>Tests` (e.g. LoginTests, DataProfileTests, etc)
    - Features: The features' name should be in snake_case, which is lower case all the words and put an underscore among the words. Like this `<feature_file_name>.feature`, (e.g. login_validation.feature, etc).
    - Suites: The suites' name is similar to features' name, like `<file_name>_testNG.xml`, (e.g. thao_testNG.xml).

  #### Functions
    The functions' name should be in camelCase. We uppercase the first letter of the second word, like `actionName`, (e.g. fillData(), goToPage(), etc).

  #### Variables
    - Constants variables: The constants variables should be uppercase all the words and there is an underscore among the words, just like this `VARIABLE_NAME`, (e.g. OVERVIEW_PAGE_NAME).
    - Enums: This is similar to the constants variables, `VARIABLE_NAME` (e.g. PIE, SINGLE_BAR, etc).
    - Global/Local variables: The global and local variables should be similar to the functions' name (in camelCase), like `globalVariableName`, `localVariableName` (e.g. panelName, pageName).

  ### Run test by suite files

  Just right click on suite files and click `Run` to execute the test.

  ### Test report [Extent Reports]

  ### How to SeleniumCore integrate with TADashboard project

    SeleniumCore is designed to manage the browser instances as well as element interactions. 
    This component helps you to create, and destroy WebDriver objects.
    To use SeleniumCore in TADashboard framework. We need to add a SeleniumCore dependency into 
    the `pom.xml` file in the TADashboard project.
    This dependency includes groupId, artifactId and version which should be matched with the declared value in SeleniumCore.
    ```
          <dependency>
              <groupId>SeleniumCore</groupId>
              <artifactId>SeleniumCore</artifactId>
              <version>0.0.3</version>
          </dependency>
    ```

  ### How to use TADashboard framework

  #### Structure project

  There are main 2 parts: main and test

  ![alt text](src\main\resources\images\define_structure_main_part.PNG)

  ![alt text](src\main\resources\images\define_structure_test_part.PNG)

  #### Web Driver options
  - `[section_name]`: [chrome.local], [ie.local], [chrome.remote], [ie.remote]
  - `mode`: local, remote
  - `driver`: chrome, ie
  - `executable`: 
  - `provider`: Selenium
  - `remoteUrl`: link hub (e.g. localhost:4444/wd/hub)
  - `arguments`:
    - `--headless`: run tests without show the browser
    - `--window-size=1280,1024`: set the size of the window
    - `--ignore-certificate-errors`: ignore all SSL errors
  - `capabilities`: 
    - `"ie.ensureCleanSession"`: When set to true, this capability clears the cache, cookies, history, and saved form data, for all running instances of Internet Explorer, including those started manually.
    - `"acceptSslCerts"`: Whether the session should accept all SSL certs by default.

  # SeleniumCore Framework

  ## Control

  ### Base

  `BaseControl` class is used to get the elements and contains all the basic methods that an element should be interacted
with.

  The controls are divided into 2 typical types: clickable, both clickable and editable.

  For the controls that are only able to be clicked, the `Clickable` class is created extending all the basic methods
in `BaseCotrol` and containing its own methods like Click().

  For the controls that are both able to be clicked and edited, the `Editable` class is created extending `Clickable`
class to use all methods in `BaseCotrol` and `Clickable` classes. Also, this class contains some more methods for
editing the controls.

  ### Common

  The classes in this package defines the specific control, they extend all methods in the classes in `base` package and
also include their own methods.

  In `common` package, if you want to define a new control, you can add a new interface extend `IClickable`, `IEditable`
or `IBaseControl` (e.g. ITable) and a class in `imp` package (e.g. Table) implement that interface.

  ### How to use control

  To use these, in your element capture class, you just need to import the classes in `imp` package in `common` as below.

       import com.logigear.control.common.imp.Button;

  Then, you initialize an element as an object of a class that you want to use. For example:

       private TextBox txtUserName = new TextBox("id=username");

  For the element, you can find by id, class, xpath, etc. For example:

       private TextBox txtUserName = new TextBox(By.id("username"));

  In this project, we can get locator by:
    - id
    - className
    - xpath
    - cssSelector
    - linkText
    - partialLinkText
    - name
    - tagName

  Copyright © by Vi's team (Vi Hoang, Anh Thao Dinh and Trang Ha).
