var app = angular.module('taskComponent', ['ui.bootstrap', 'ngResource']);

app.component('taskComponent', {
  templateUrl: 'task/task.template.html',
  controller: function TaskController($uibModal, Task) {
    var $ctrl = this;
    refreshOrInitTasks();

    // Refresh tasks
    function refreshOrInitTasks() {
      $ctrl.tasks = Task.query();
      $ctrl.newTask = { title: '', description: '', status: '' };
    }

    // Add task
    $ctrl.addTask = function (task) {
      isValid = isTaskValid(task);
      if (!isValid) { alert('Title is required!'); return; } // TODO: Better error handling
      Task.save(task).$promise.then(function () {
        refreshOrInitTasks();
      }).catch(function (response) {
        $ctrl.errMsg = response;
      });
    }

    // Delete task
    $ctrl.deleteTask = function (taskId) {
      var taskIdObj = { taskId: taskId };

      if (!confirm("Are you sure?")) { return; }

      Task.delete(taskIdObj).$promise.then(function () {
        console.log('Task deleted successfully');
        refreshOrInitTasks();
      }).catch(function (response) {
        console.error('Error deleting task:', response);
        $ctrl.errMsg = response;
      });

    };


    // Toggle edit/update modal
    $ctrl.toggleModal = function (task) {
      var modalInstance = $uibModal.open({
        animation: false,
        ariaLabelledBy: 'modal-title',
        ariaDescribedBy: 'modal-body',
        templateUrl: 'task-modal/task-modal-form.html',
        controller: 'modalController',
        controllerAs: '$ctrl',
        size: 'lg',
        resolve: { task: function () { return task; } }
      });

      modalInstance.result.then(function (updatedTask) {
        isValid = isTaskValid(updatedTask);
        if (!isValid) { alert('Title is required!'); return; }
        Task.update(updatedTask).$promise.then(function () {
          refreshOrInitTasks();
        }).catch(function (response) {
          $ctrl.errMsg = response;
        });
      });
    };

    // Set task as completed
    $ctrl.setAsCompleted = function (task) {
      task.status = 'Completed';
      Task.update(task).$promise.then(function () {
        refreshOrInitTasks();
      }).catch(function (response) {
        $ctrl.errMsg = response;
      });
    };

  }
});

app.controller('modalController', function ($uibModalInstance, task) {
  var $ctrl = this;
  $ctrl.task = angular.copy(task);
  $ctrl.ok = function () { $uibModalInstance.close($ctrl.task); };
  $ctrl.cancel = function () { $uibModalInstance.dismiss('cancel'); };
});

app.controller('taskFormController', function ($scope) {
  $scope.addTask = function () {
    // same logic here
  }
});


// simple validation
function isTaskValid(task) {
  if (!task || !task.title) {
    return false;
  } else {
    return true;
  }
}

app.directive('ngConfirmClick', [
  function () {
    return {
      link: function (scope, element, attr) {
        var msg = attr.ngConfirmClick || "Are you sure?";
        var clickAction = attr.confirmedClick;
        element.bind('click', function (event) {
          if (window.confirm(msg)) {
            scope.$eval(clickAction)
            scope.$apply()
          }
        });
      }
    };
  }]);


