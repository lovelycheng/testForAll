package tech.lovelycheng.testForAll.gitlab4jtest;

import org.gitlab4j.api.Constants;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.Branch;
import org.gitlab4j.api.models.CompareResults;
import org.gitlab4j.api.models.Project;
import org.openjsse.sun.security.ssl.SSLLogger;

import java.util.List;
import java.util.logging.Level;

/**
 * @author chengtong
 * @date 2022/4/7 14:29
 */
public class GitlabTest {

    public static String USER_NAME = "chengt26050";
    public static String PASSWORD = "LOVEYXL123chengt";
    public static String GITLAB_URL = "http://gitlab.yunrong.cn/";
    public static String BRANCH_3052_1236 = "snapshot/3.0.5.X-1236";
    public static String BRANCH_3051 = "snapshot/3.0.5.1-BHYH";

    public static void main(String[] args) throws GitLabApiException {
        GitlabTest gitlabTest = new GitlabTest();
        gitlabTest.login();
    }

    private void login() throws GitLabApiException {

        GitLabApi gitLabApi = GitLabApi.oauth2Login(GITLAB_URL, USER_NAME, PASSWORD);
        gitLabApi.enableRequestResponseLogging(Level.ALL);
        List<?> projects = gitLabApi.getSearchApi().globalSearch(Constants.SearchScope.PROJECTS, "guard");

        Project guard = (Project) projects.get(0);
        List<Branch> branches = gitLabApi.getRepositoryApi().getBranches(guard.getId());

        Branch branch3051 =
                branches.stream().filter(branch -> branch.getName().equals(BRANCH_3051)).findFirst().get();
        Branch branch30521236 =
                branches.stream().filter(branch -> branch.getName().equals(BRANCH_3052_1236)).findFirst().get();


        CompareResults compareResults = gitLabApi.getRepositoryApi().compare(guard.getId(), BRANCH_3051, BRANCH_3052_1236);

        System.err.println(compareResults);

    }




}
