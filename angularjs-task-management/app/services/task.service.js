angular.module('taskService', ['ngResource']).factory('Task', function ($resource) {
    var baseUrl = 'http://localhost:8080/tasks';
    return $resource(baseUrl, {}, {
        query: {
            method: 'GET',
            isArray: true,
            interceptor: {
                response: function (response) {
                    return response;
                }
            }
        },
        save: {
            method: 'POST',
            url: baseUrl + '/createTask',
            body: { task: '@task' },
            interceptor: {
                response: function (response) {
                    return response;
                }
            }
        },
        update: {
            method: 'PUT',
            url: baseUrl + '/updateTask',
            body: { task: '@task' },
            interceptor: {
                response: function (response) {
                    return response;
                }
            }
        },
        delete: {
            method: 'DELETE',
            url: baseUrl + '/deleteById',
            param: { taskId: '@taskId' },
            headers: {'Content-Type': 'application/json'},
            interceptor: {
                response: function (response) {
                    return response;
                }
            }
        }
    });
});