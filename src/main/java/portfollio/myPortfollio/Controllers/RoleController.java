package portfollio.myPortfollio.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import portfollio.myPortfollio.Services.RoleService;
import portfollio.myPortfollio.dtos.request.RoleRequest;
import portfollio.myPortfollio.dtos.response.ApiResponse;
import portfollio.myPortfollio.dtos.response.RoleResponse;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @GetMapping("")
    public ApiResponse<List<RoleResponse>> getAllRoles() {
        List<RoleResponse> role = roleService.getAll();

        return ApiResponse.<List<RoleResponse>>builder().data(role).build();
    }

    @GetMapping("/{name}")
    public ApiResponse<RoleResponse> getRole(@PathVariable String name) {

        return ApiResponse.<RoleResponse>builder()
                .data(roleService.getRole(name))
                .build();
    }

    @PostMapping("")
    public ApiResponse<RoleResponse> createRoles(@RequestBody RoleRequest request) {

        RoleResponse permission = roleService.create(request);

        return ApiResponse.<RoleResponse>builder().data(permission).build();
    }

    @DeleteMapping("/{role}")
    public ApiResponse<Void> deleteRoles(@PathVariable String role) {
        roleService.delete(role);

        return ApiResponse.<Void>builder().build();
    }
}
