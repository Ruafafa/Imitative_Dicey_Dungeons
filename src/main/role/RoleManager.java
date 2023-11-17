package main.role;

/**
 * 角色需要扩展、使用 Manager管理
 */
public class RoleManager {
    /**
     * 可使用的角色
     */
    private  final Role[] availableRoles;

    /**
     * 当前战斗的角色
     */
    private Role combatRole;

    public RoleManager() {
        // 初始化
        availableRoles = new Role[]{
                new Role("云云", 100),
                new Role("杰", 200),
                new Role("Aris", 60)
        };
    }

    /**
     * 展示角色列表
     */
    public void showRoleList() {
        // 选择角色列表展示
        for (int i = 0; i < availableRoles.length; ++i) {
            System.out.printf("[%d] %s\n", i, availableRoles[i]);
        }
    }


    /**
     * 选人逻辑模块
     * : 选人成功返回 true
     * @return boolean
     */
    public boolean chooseRole(Integer index) {
        // 操作
        if (0 > index || index >= availableRoles.length) {return false;}
        combatRole = availableRoles[index];
        return true;
    }


    public Role getCombatRole() {
        return combatRole;
    }

    public void setCombatRole(Role combatRole) {
        this.combatRole = combatRole;
    }

}
